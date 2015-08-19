var require,
	define;

/**
 * simple implementation of js-module loading mechanism
 */

(function(){

	function Loader(){
		this._modules = {};
		this._logIndent = 0;
	}

	Loader.prototype = {
		log: function(msg){
			var padding = '';
			for(var i = this._logIndent; i>0 ; --i) {
				padding += '    ';
			}
			print(padding + 'T : ' + msg.replace(/\n/g,'\n'+padding));
		},
		define: function define(/* [name,] [dependencies,] factory */) {
			var args = Array.prototype.slice.apply(arguments),
				factory = args.pop(),
				name = args[0],
				dependencies;

			if ('string' === typeof name) {
				dependencies = args[1];
			} else {
				dependencies = name;
				name = this._nameFromPath(jsEngine.getPath());
			}
			
			if (!dependencies || !dependencies.length) {
				dependencies = [];
			}

			var module = this._requireArray(dependencies, factory);
			this.log('defining module '+name);
			this._modules[name] = module;
		},
		require: function require(dependencies, callback) {
			if ('string' === typeof dependencies) {
				return this._requireOne(dependencies)
			} else {
				return this._requireArray(dependencies, callback);
			}
		},
		_requireOne: function _requireOne(moduleName) {
			if (!(moduleName in this._modules)) {
				this.log('_requireOne loading: '+moduleName);
				this._load(moduleName);
			}
			var module = this._modules[moduleName]
			return module;
		},
		_requireArray: function _requireArray(dependencies, callback) {
			var callbackArgs = [];
			
			this.log('Required dependencies: '+dump(dependencies));
			for (var index in dependencies) {
				callbackArgs.push(this._requireOne(dependencies[index]));
			}
			this.log('Resolved dependencies: '+dump(callbackArgs));
			
			return 'function' === typeof callback
				? callback.apply(undefined, callbackArgs)
				: callback;
		},
		_load: function _load(name){
			++ this._logIndent;
			jsEngine.load('modules/'+name);
			-- this._logIndent;
		},
		_nameFromPath: function _nameFromPath(path) {
			return path
				.replace(/.js$/, '')
				.replace(/^.*\//, '');
		},
	};
	Loader.prototype.constructor = Loader;
	
	var loader = new Loader();
	/**
	 * define([name,] [dependencies,] factory)
	 * @param name string (optional) - name of defined module. If omitted, filename is used
	 * @param dependencies array - names of required modules
	 * @param factory function(dependency1, dependency2, ...)|mixed - factory function or module
	 */
	define = function() {
		return loader.define.apply(loader, arguments);
	};
	/**
	 * require(dependencies [, callback])
	 * @param dependencies array - names of required modules
	 * @param callback function(dependency1, dependency2, ...)
	 *
	 * require(name)
	 * returns module by name
	 * @param name string - name of module
	 * @returns mixed - module
	 */
	require = function() {
		return loader.require.apply(loader, arguments);
	};
})()