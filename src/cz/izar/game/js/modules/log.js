define(function(){
	var log = function(msg) {
		print("[JS] "+msg);
	}
	log.error = function (msg) {
		print("[JS ERROR] "+msg);
//		java.lang.System.error.println("[JS ERROR] "+msg);
	};
	return log;
});



