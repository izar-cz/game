

game.addBlueprint({
	id: 'boulder',
	name: "Boulder",
	description: "A big granite boulder.",
	passability: false,
	presentation: {
		sprite: 'tiles01-5-3',
	},
},{
	id: 'bucket',
	name: "Bucket",
	item: {
		mass: 800,
		volume: 1000,
		diameter: 600,
	},
	presentation: {
		sprite: 'tiles01-6-3',
	},
},{
	id: 'tree01',
	name: "Oak",
	presentation: {
		sprite: 'trees-0-0',
	},
},{
	id: 'tree02',
	name: "Oak 02",
	presentation: {
		sprite: 'trees-1-0',
	},
},{
	id: 'tree03',
	name: "Dry tree",
	presentation: {
		sprite: 'trees-2-0',
	},
},{
	id: 'campfire',
	name: "Burning Campfire",
	presentation: {
		sprite: 'campfire-0',
	},
},{
	id: 'bird',
	name: "Flying bird",
	intelligence: true,
	presentation: {
		dynamicSprite: 'bird',
	},
},{
	id: 'skeleton',
	name: "Dangerous skeleton",
	intelligence: true,
	presentation: {
		dynamicSprite: 'skeleton',
	},
},{
	id: 'shield',
	name: "Wooden shield",
	item: {
		mass: 3000,
		volume: 3000,
		diameter: 1000,
	},
	listeners: {
		pregrab: function(e) {
			log('pre grab shield');
		},
		postgrab: function(e) {
			log('post grab shield');
		}
	},
	presentation: {
		sprite: 'shield-0-1',
		dynamicSprite: 'shield',
	},
},{

	/* complex entity example (not implemented yet) */
	
	// basic info
	id: 'cabbage01',
	name: "Cabbage",
	description: "A tasty cabbage",
	
	// "plugins"
	item: { // enables 'item' functionality
		weight: 6,
	},
	consumable: { // this entity can be consumed
		common: true, // suppress confirmation request. should be moved to being dictionary
		description: "The Cabbage is delicious!",
		nutrients: {
			energy: 20,
			water: 10,
		},
	},

	// data for presentation adapters
	presentation: {
		color: '#33EE00',
		sprite: 'tiles01-6-2',
//		inventorySprite: 'tiles01-6-2',
//		descriptionSprite: 'tiles01-6-2',
	},

	// Dictionary data. Doesn't belong to the entity, but it is convenient to have it here.
	dictionary: {
		// ...
	}
});
