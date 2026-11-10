# 🧟 The Last Shift
A Java Console-Based Survival Horror Game

Requires JDK 27

A console-based survival-horror game written in Java by three people (group 7). 
You wake at your desk after falling asleep during your last shift. The office is dark and empty, 
and outside a zombie outbreak has brought the city to its knees. Your only goal is to get home to your partner,
but the road runs straight through the heart of the outbreak.

## ✨ Game Features

1. Explore nine hand-crafted locations, from the office where your shift begins to the garage where it ends
2. Search rooms, scavenge supplies and talk to co-workers
3. Fight turn-based battles with melee, ranged and heavy attacks
4. Face different infected: civilians, runners, heavy infected and a final encounter that hits close to home
5. Manage an inventory of weapons,armor food and medical supplies
6. Equip, unequip and use items, such as a Glock 19, Desert Eagle, fire axe and motorcycle jacket, plus first aid kits, 
bandages, antibiotics, antiseptic, painkillers and energy drinks
7. Power up your next attack by drinking an energy drink
8. Track your character's stats and the bonuses from equipped gear
9. Find the car keys, which you need to escape the city
10. Hear synthesized music and sound effects
11. Play with colored console output to set the mood


## 📁 Project structure

| Path | Purpose |
|------|---------|
| `src/main/java/armor/` | Armour and protective gear |
| `src/main/java/audio/` | Synthesized sound |
| `src/main/java/combat/` | Characters, enemies, attacks, and battles |
| `src/main/java/food/` | Food items |
| `src/main/java/game/` | Game engine and menus |
| `src/main/java/inventory/` | Inventory management and item use |
| `src/main/java/item/` | Base item classes |
| `src/main/java/keyitem/` | Key items |
| `src/main/java/medkit/` | Medical supplies |
| `src/main/java/org/example/` | Maven example scaffold |
| `src/main/java/roomMechanics/` | Rooms, exploration, and world |
| `src/main/java/weapon/` | Weapons |
| `src/test/java/` | JUnit tests, organized by package |


## 🚀 Getting started
In IntelliJ IDEA:

1. Navigate to File → New → Project from Version Control.
2. Paste the repository URL: https://github.com/ithsjava26/Grupp-7
3. Click Clone.
4. IntelliJ IDEA should recognize the pom.xml file and load the project as a Maven project.
5. Set the project language level to Java 27 (Preview) if necessary.
6. Run the Game
   Run MainMenu (the green play button). The main menu should appear.
   Or in the terminal, from the project root:


## 🕹️ How to play the game:
Start a new game, name your character, and explore each room. Manage your inventory or check your status between
encounters. When enemies appear, the battle continues until one side falls.

| Option | Action |
|:------:|--------|
| 1 | Explore 
| 2 | View inventory |
| 3 | View character status |
| 4 | Continue to Office Hallway |
| 5 | Save game |
| e | Return to MainMenu |


## 👥 Responsibilities within the group: 
Each team member was responsible for specific packages and modules. But we all helped each other
and reviewed each other's code.

| Package | Contains | Module | Owner |
|---------|----------|--------|-------|
| combat | Hero, enemies, attack strategies and battles | Combat & characters | Najima (A) |
| item | Base item classes | Items & inventory | Leonardo (B) |
| weapon | Weapons | Items & inventory | Leonardo (B) |
| armor | Armour, including the motorcycle jacket | Items & inventory | Leonardo (B) |
| food | Food items | Items & inventory | Leonardo (B) |
| medkit | Medical supplies | Items & inventory | Leonardo (B) |
| keyitem | Key items, including the car keys | Items & inventory | Laura (C) |
| inventory | Inventory management and item use | Items & inventory | Laura (C) |
| game | Game engine, main menu and gameplay menu | Engine, world & saving | Laura (C) |
| roomMechanics | Rooms, world, exploration and console colors | Engine, world | Laura (C) |
| audio | Synthesized music and sound effects | Laura(C) |
| README | Project documentation | Najima (A) |


## 💭Reflection
What Worked Well

We worked collaboratively throughout the project. Whenever someone encountered a problem, the other team members 
stepped in to help. This allowed us to complete the project together rather than working as three separate individuals.

One owner per package. Assigning responsibility for specific packages allowed us to work simultaneously while minimizing
the risk of conflicts caused by editing the same files.

Small pull requests with code reviews. Breaking our work into smaller changes made the code easier to understand and 
review. Before merging, our code was reviewed by a group member and, when applicable, by CodeRabbit.

Frequent commits and feature branches. We developed our features in small steps and used Git branches to keep
our work organized.

### Bugs, Errors, and Merge Conflicts

During the initial stages of development, we encountered several bugs, errors, and merge conflicts. We worked together 
to resolve these issues and sought help from our teacher and CodeRabbit when necessary. Through collaboration, 
communication, and asking for help when needed, we became better at identifying and solving problems throughout 
the development process.

Group reflektion on Git Collaboration

Over the course of the project, we gained valuable experience collaborating through Git and GitHub. We used separate branches, pull requests and code reviews to develop and integrate the game's different modules. In practice, the handling of pull requests, code reviews and merges was mainly done by two group members.

One of the project's biggest challenges was an uneven distribution of effort and responsibility, as well as a lack of communication during parts of the development process. During the first week, one group member had no commits, which made it difficult to see how the work was progressing. Even after we followed up, it took further time before the work became visible in our shared repository.

We also had difficulties coordinating the modules with each other. Some functionality was implemented outside the requirements we had agreed on together, for example level requirements for equipment even though the game has no level system. This meant extra work adapting the code and making sure the different systems worked together.

During the final week of the project, basic functionality for the inventory, item pickup and a number of finished items was delivered. These formed a useful foundation for further development, but they needed to be completed and integrated with the other systems to work in the finished game.

The division of work therefore ended up differing from the original plan. Two group members had to take greater responsibility for the project's coordination, follow-up, code review, integration and further development, in addition to their own assigned modules. We handled the challenges through clearer task descriptions, recurring check-ins, joint debugging and adjustments to the code. At the same time, we could have worked in a more structured way, with shared milestones, clear deadlines and regular updates on the status of the work.

An important lesson is that a well-functioning Git collaboration is not only about technical tools, but also requires transparency, continuous communication, independent responsibility and an understanding of how the different modules work together.

### What we learned
We learned how classes from three different packages interact within a single program through the use of abstract
classes, interfaces, and composition.

We also gained experience in writing JUnit tests, including techniques for making random behavior testable.

Most importantly, we learned how to collaborate effectively as a team using Git branches, pull requests, and GitHub.
We reviewed each other's code and received additional feedback from AI-powered code review tools such as CodeRabbit.

## 📚 Sources and use of AI
The synthesized music and audio were generated by Claude AI.

We used course materials and lectures to understand programming concepts. We also took help from the teacher and 
CodeRabbit. We used AI tools also to get a structured overview of the project and how to divide the work between the group. 

## 🧪 JUnit Testing

We wrote JUnit tests for parts of the code that we considered important to verify and where bugs could be difficult 
to identify manually. JUnit testing was particularly useful for detecting bugs and errors early in the development 
process. It also helped us verify that our code behaved as expected when making changes.
