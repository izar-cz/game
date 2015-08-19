
function dump(variable,padding) {
	var dumped_text = "",
		type = typeof(variable);
	padding = padding || '';

	switch(type) {
	case 'function':
		dumped_text += "[function] ";
	case 'object':
		var nested_padding = padding+'    ',
			items = [];
		for (var key in variable) {
			var value = variable[key];
			items.push( nested_padding + "'" + key + "' => " + dump(value, nested_padding) );
		}
		if (items.length) {
			dumped_text += "{\n"+items.join('')
					+ padding+"}";
		} else {
			dumped_text += "{}";
			if('function' != type) {
				dumped_text += " ("+variable+")";
			}
		}
		break;
	case 'undefined':
		dumped_text = variable;
		break;
	case 'string':
		dumped_text = '"'+variable+'" ('+type+')';
		break;
	default:
		dumped_text = variable+' ('+type+')';
	}
	return dumped_text + "\n";
}