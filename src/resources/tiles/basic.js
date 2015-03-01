/**
 * basic TILES resource
 */

game.addTileType({
	id: 'void',
	passability: true,
	'default': true,
	presentation: {
		sprite: 'tiles01-2-2'
	}
},{
	id: 'grass',
	passability: true,
	presentation: {
		sprite: 'tiles01'+'-0-0'
	}
},{
	id: 'dirt',
	passability: true,
	presentation: {
		sprite: 'tiles01-3-1'
	}
},{
	id: 'field',
	passability: true,
	presentation: {
		sprite: 'tiles01-3-1'
	}
},{
	id: 'meadow',
	passability: true,
	presentation: {
		sprite: 'tiles01-2-0'
	}
},{
	id: 'chasm',
	passability: false,
	presentation: {
		sprite: 'tiles01-0-1'
	}
});

