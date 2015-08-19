

define(['log'], function(log){

	var game = {};
	var _world = world;
	var _entityManager = world.getEntityManager();
	var _tileManager = world.getTileManager();
	var _uidManager = uidManager;
	var gamePackage = function(name){
		return Java.type('cz.izar.game.'+name);
	};

	var properties = {
			1:  'name',
			2:  'description',
			20: 'is_item',
			21: 'mass',
			22: 'diameter',
			23: 'volume',
		};

	game.addTileType = function() {
		if( ! _tileManager ) {
			log.error("tileManager not initialized!");
			return;
		}
		if( ! _tileManager instanceof gamePackage('map.TileManager') ) {
			log.error("tileManager not instanceof cz.izar.game.map.TileManager!");
			return;
		}
		var length = arguments.length;
		for ( var i = 0 ; i < length ; ++i ) {
			_addTileType(arguments[i]);
		}
	};
	game.addBlueprint = function() {
		if( ! _entityManager ) {
			log.error("entityManager not initialized!");
			return;
		}
		if( ! _entityManager instanceof gamePackage('entity.manager.EntityManager') ) {
			log.error("entityManager not instanceof cz.izar.game.entity.manager.EntityManager!");
			return;
		}
		var length = arguments.length;
		for ( var i = 0 ; i < length ; ++i ) {
			// TODO: inheritance
			_addBlueprint(arguments[i]);
		}
	};
	game.addEnvironment = function() {
//		if( ! _entityManager ) {
//			log.error("entityManager not initialized!");
//			return;
//		}
//		if( ! _entityManager instanceof gamePackage('manager.EntityManager') ) {
//			log.error("entityManager not instanceof cz.izar.game.entity.manager.EntityManager!");
//			return;
//		}
		var length = arguments.length;
		for ( var i = 0 ; i < length ; ++i ) {
			_addEnvironment(arguments[i]);
		}
	};

	function _addEnvironment(config) {
		var entityConfig = config.entity,
			mapConfig = config.map,
			mapWidth = mapConfig.width,
			mapHeight = mapConfig.height;


		var environment = new (gamePackage('Environment'))( mapWidth, mapHeight ),
			_placeEntity = function( blueprintId, x, y ) {
				log( 'placing entity "'+blueprintId+'" at ('+x+', '+y+')' );
				var entity = _entityManager.createEntity(blueprintId);
				environment.placeEntity( entity, new (gamePackage('map.Coordinates'))(x, y) );
			};

		_world.appendChild( environment );

		if ('string' === typeof mapConfig.map) {
			mapConfig.map = mapConfig.map.split("\n");
		}

		for(var symbol in mapConfig.legend ) {
			var description = mapConfig.legend[symbol];
			if('string' === typeof description) {
				mapConfig.legend[symbol] = {tile:description};
			}
		}
		
		var x,y, tileConfig, tile;
		for( y=0 ; y<mapHeight ; ++y ) {
			var row = mapConfig.map[y];
			for( x=0 ; x<mapWidth ; ++x ) {
				tileConfig = mapConfig.legend[row[x]];
				if( undefined === tileConfig ) {
					throw 'invalid tile';
				}
				tile = _tileManager.createTile(tileConfig.tile);
				environment.setNodeAt(new (gamePackage('map.Coordinates'))(x, y), tile);
				if( tileConfig.entity ) {
					_placeEntity( tileConfig.entity, x, y );
				}
			}
		}
		
		
		for ( var i=0, l=entityConfig.length ; i<l ; ++i ) {
			var cfg = entityConfig[i],
				loc = cfg.location;
			_placeEntity( cfg.blueprint, loc.x, loc.y );
		}
	}



	function _addBlueprint(config) {
		var uid = _uidManager.next();
		var blueprint = _createComplexBlueprint(config, uid);
		_entityManager.addBlueprint(blueprint);
	}



	function _addTileType(config) {
		var name = ''+config.id,
			passability = !!config.passability;
		
		log('Adding tile type "'+name+'"');
		var type = new (gamePackage('map.TileType'))( name, passability );

		if ( config.presentation ) {
			type.setPresentation( new (gamePackage('presentation.SimplePresentation'))( config.presentation ) );
		}

		_tileManager.addType(type);
		if ( config['default'] ) {
			_tileManager.setDefault(type);
		}
	}
	
	function _createSimpleBlueprint(config, uid) {
		var name = ''+config.id,
			passability = !!config.passability,
			entityName = config.name || name;
		log('Adding simple blueprint "'+name+'"');
		return gamePackage('entity.manager.SimpleBlueprint')(uid, name, ''+entityName, passability);
	}
	
	function _createComplexBlueprint(config, uid) {
		var name = config.id;
		log('Adding complex blueprint "'+name+'"');
		var blueprintCore = new (gamePackage('entity.manager.ComplexBlueprintCore'))({
			createCore: function(){
				var entity = _cloneConfig(config);
				var presentation = new (gamePackage('presentation.Presentation'))({
					getMap: function(key) {
						return null;
					},
					getString: function(key) {
						var value = _resolve(entity.presentation[key], entity);
						return (undefined === value || null === value)
							? null
							: ""+value;
					}
				});
				return new (gamePackage('entity.ComplexEntityCore'))({
					getPassability: function(){ return !!_resolve(entity.passability, entity); },
					getPresentation: function(){ return presentation; },
					getProp: function(propType){ return ""+_resolve(entity[properties[propType]], entity); },
					handle: function(event) {
						if(event instanceof gamePackage('entity.event.ActionEvent') ) {
							var action = event.getAction(),
								target = action.getTarget();
							if (null !== target) {
								if (target instanceof gamePackage('entity.ComplexEntity')) {
								}
							}
						}
						
						var type = event.getType(),
							phase = event.getPhase();
						if(entity.listeners && entity.listeners[phase+type]) {
							entity.listeners[phase+type].call(entity,event);
						} else if ('pre' === phase && 'grab' === type) {
							log('ungrabable');
							event.preventDefault();
						}
					}
				});
			}
		});
		return config.intelligence
			? new (gamePackage('entity.manager.BeingBlueprint'))(uid, name, blueprintCore)
			: new (gamePackage('entity.manager.ComplexBlueprint'))(uid, name, blueprintCore);
	}
	function _cloneConfig(config) {
		return config;
	}
	function _resolve(value,subject) {
		if( 'function' === typeof value ) {
			return value.call(subject); // TODO
		}
		return value;
	}
	
	print('GAME ok'+game)
	return game;
});

