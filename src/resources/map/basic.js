/**
 * 
 */
game.addEnvironment({
	name: 'samplePlains',
	entity: [{
		blueprint: 'skeleton',
		location: {x:4,y:5}
	},{
		blueprint: 'skeleton',
		location: {x:14,y:5}
	},{
		blueprint: 'skeleton',
		location: {x:16,y:2}
	},{
		blueprint: 'bird',
		location: {x:15,y:9}
	},{
		blueprint: 'campfire',
		location: {x:8,y:5}
	},{
		blueprint: 'shield',
		location: {x:14,y:5}
	},{
		blueprint: 'shield',
		location: {x:8,y:6}
	},{
		blueprint: 'bucket',
		location: {x:9,y:5}
	}],
	map: {
		width: 20,
		height: 20,
		legend: {
			'~': 'field',
			'.': 'grass',
			',': 'meadow',
			'T': {
				tile: 'grass',
				entity: 'tree01',
			},
			't':{
				tile: 'grass',
				entity: 'tree02',
			},
			'b':{
				tile: 'grass',
				entity: 'shield',
			},
		},
		map: [
//		 01234567890123456789
		'.,..................',
		'..........b.....b...',
		'.~~~.......,...b.b..',
		'.~~~....T.......b...',
		'.~~~.........,......',
		'......,...........,.',
		'...T.,......T.......',
		'.......t............',
		'..,.......,.......,.',
		'.....,.........t....',
		'...,....T...........',
		',............,......',
		'..t....,.......,....',
		'....................',
		'..,...T....,...b....',
		'........,........,..',
		'...,................',
		'......,...b...t.....',
		'..........,.........',
		'................,...',
		],
	}
});
