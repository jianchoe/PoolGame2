# PoolGame

**Note 1:** The friction value in the config file is used as a multiplier in the
implementation. As the friction approaches 0, the friction decreases. As friction
approach 1, the friction increases. A high value of friction will make it 
impossible for a ball to move. Range of valid friction is: `0 < friction < 1`.

**Note 2:** While the forces applied to the cue ball is variable based on the
length of the line shown when dragging from the cue ball, there is a maximum cap
on the force.

**Note 3:** The center of the ball needs to be in the pocket for the code to 
consider it as "in the pocket" instead of its rectangular bound just intersecting
with the pocket's rectangular bound.

## Commands

* Run: `gradle run` to load default config from resources folder or 
`gradle run --args="'insert_config_file_path'"` to load custom config.

* Generate documentation:`gradle javadoc`

* Features implemented:
  * Configurable Pockets (Extended on Config Factory and used Builder pattern)
  * More Coloured Balls (Extended on Ball Builder and Pocket Strategy)
  * Difficulty Level (State pattern)
  * Time
  * Score (Strategy pattern)
  * Cheat (Strategy Pattern)
  * Undo (Memento pattern (Not currently working))

* Patterns Used -> All classes/package involved:
  * Builder -> (PocketBuilder package)
  * State -> (State package)
  * Strategy -> (ScoringStrategy & CheatStrategy packages)
  * Memento -> (Memento package)

* Difficulty Selection: Starts with EASY Difficulty
  * Press **Q** for **Easy** difficulty (config_easy.json)
  * Press **W** for **Normal** Difficulty (config_normal.json)
  * Press **E** for **Hard** Difficulty (config_hard.json)
  * Press **A** for **Original** Level (config.json)

* Cheat Selection:
  * Press **Z** to cheat **RED** balls
  * Press **X** to cheat **YELLOW** balls
  * Press **C** to cheat **GREEN** balls
  * Press **V** to cheat **BROWN** balls
  * Press **B** to cheat **BLUE** balls
  * Press **N** to cheat **PURPLE** balls
  * Press **M** to cheat **BLACK** balls
  * Press **K** to cheat **ORANGE** balls

* Undo:
  * Press **R** to **UNDO** (Currently not working)
