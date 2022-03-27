# Draft Class Reader/Writer
### For the 2022 version of a very popular professional football video game
### UNOFFICIAL: the creators of said video game are in no way involved with this project

## Description

This tool converts draft class data between the 2022 football video game binary files and CSV files.
The CSV-formatted data provides an easy way to display and edit the draft class.

The tool can also help players bypass the game's scouting system without modifying the game. 
Note that exporting a draft class will reveal all player attribute ratings and development traits.
Scheme fits and accurate overall ratings are not supported but can be inferred from attribute ratings.

## Usage
#### This program was written in Java 15. You may need to update your JDK to run this program. You can find the latest version here: https://www.oracle.com/java/technologies/downloads/

### In-game steps

**Saving a draft class**: A draft class can be saved from a franchise game at any point during scouting by going to "Scout College Players", then "Prospects", "Edit Prospects", choose "Edit Draft Class", then exit the edit menu and choose "Export Local File". The file will be located with your game's offline save data.

**Loading a draft class**: Ensure the edited draft class (in binary form without a file extension) is located alongside the game's other offline save data. In a franchise game during scouting, go to "Scout College Players", then "Prospects", "Edit Prospects", choose "Import Local File", and select the updated file.

**Offline save data location is usually** `C:\Users\username\Documents\the_games_name_here\saves\file_name_here`

### Running the tool

In Command Prompt (cmd.exe), navigate to the location of DraftClassTool.jar and run:

`java -jar DraftClassTool.jar` followed by an `export` or `import` command.

Example:
```
java -jar DraftClassTool.jar export "C:\Users\foo\Documents\game\saves\CAREERDRAFT-FOOBAR" "C:\Users\foo\Desktop\output.csv"
```
Alternatively, you can create a batch file in the same directory as DraftClassTool.jar. In a batch file (maybe "run.bat"), add code like this, following the previous example:

```
@ECHO OFF
java -jar DraftClassTool.jar export "C:\Users\foo\Documents\game\saves\CAREERDRAFT-FOOBAR" "C:\Users\foo\Desktop\output.csv"
PAUSE
```

## Export draft classes to CSV files

This command will turn a binary draft class file into a CSV file, which can be opened in a spreadsheet program for easy viewing and editing.

`export "classPath" "csvPath"`

* classPath: the location of the draft class file to be converted to CSV
* csvPath: the location of the CSV file to be created
* both of these must be in quotes

## Import CSV files into draft class files

This command will turn a CSV file with player data into a properly formatted binary draft class file.

`import "csvPath" "templatePath" "classPath"`

* csvPath: the location of the CSV file that will be imported into the draft class file
* templatePath: the location of any draft class file. This will be used as a template for everything the CSV file doesn't cover, such as player traits, appearance, equipment, and more.
* classPath: the location of the draft class file to be created
* all of these must be in quotes

## Important Usage Info
The following player data is supported by this tool:

|Data|Export|Import|Info|
|:---:|:---:|:---:|---|
|Name|x|x|First name can be up to 13 characters and last name can be up to 15 characters.
|Position|x|x|Ensure positions are capitalized. Players will default to punter position if there is an error.|
|Projected Pick|x|-|Only for players projected to be drafted.|
|Age, Height, Weight|x|x|Must be within game limits: age between 20 and 40, height between 65 and 84 inches, weight between 160 and 400 pounds.|
|Development Trait|x|x|This is the only player trait supported. Ensure traits are capitalized. Players will default to normal trait if there is an error.|
|Ratings|x|x|Individual ratings for each player attribute. Ratings must be between 0 and 99 for each attribute.|
|Game Version 2016 Overall Rating|x|-|Called "M16OVR" in the CSV header. This is calculated by the tool when exporting a draft class to a CSV file. The formula used follows tables found online to calculate the player's overall rating from their attributes. These ratings are not accurate to the 2022 version of the game.|

* For a draft class to be recognized by the game, it must be named `CAREERDRAFT-` followed by a string of capital letters and/or numbers and must not have a file extension.
* An inputted CSV file must have 451 rows, where the first is a header and the rest are the 450 players in the draft class.
* The CSV file's first row (header) must have the exact same values in the exact same order as an outputted CSV from the `export` command. The columns will be processed in this order, so ensure the column order is correct when editing.
* There are 2 unknown variables lumped in with the player ratings in the binary code, named "UNKNOWN1" and "UNKNOWN2" in the CSV header. UNKNOWN1 seems to be 1 for all players. UNKNOWN2 might be a quarterback attribute. 
