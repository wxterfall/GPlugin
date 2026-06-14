# GPlugin

A custom SkyMining core plugin for a Minecraft server.

## Description

GPlugin is a custom Minecraft Paper plugin designed to act as the core foundation for a SkyMining server. It includes essential server systems such as commands, staff tools, scoreboard support, configurable tablist styling, playtime tracking, and a rank manager. The plugin is built to support a custom SkyMining experience, with future plans for mines, progression, gangs, rewards, economy systems, and other server-specific gameplay features.

The project is currently being developed as a modular server core, meaning features are separated into commands, listeners, settings, user interface systems, utilities, and rank management classes. This makes the plugin easier to expand as the server grows.

## Getting Started

### Dependencies

Before installing or building the plugin, make sure you have:

* Java 17 or newer
* Paper/Spigot server running Minecraft 1.20.4+
* Maven or Gradle

### Installing

To install the plugin on a server:

* Download or build the latest plugin `.jar` file.
* Place the compiled `.jar` file into your server's `plugins` folder.
* Start the server once to generate the plugin folder and configuration files.
* Edit the generated files inside:

```txt
plugins/GPlugin/
```

The main configuration file is:

```txt
plugins/GPlugin/config.yml
```

Example tablist configuration:

```yml
tab:
  animated: false

  header:
    - ""
    - "<yellow><bold>GPlugin</bold></yellow>"
    - "<gray>Playing at <gold>SkyMining</gold></gray>"
    - ""

  footer:
    - ""
    - "<yellow>{online}</yellow> <gray>players online</gray>"
    - "<gold>play.yourserver.com</gold>"
    - ""
```

### Executing program

To run the plugin:

* Build the project into a `.jar` file.
* Copy the `.jar` into your Minecraft server's `plugins` folder.
* Start the server.
* Configure the plugin files if needed.
* Restart the server after making configuration changes.

If using Maven:

```bash
mvn clean package
```

The compiled plugin jar will usually be located in:

```txt
target/
```

Then copy it to:

```txt
server/plugins/
```

Start the server using your normal server startup command, for example:

```bash
java -Xms2G -Xmx4G -jar paper-1.20.4.jar nogui
```

## Features

### Current Features

* Custom tablist header and footer
* Toggleable animated/static tablist
* Custom scoreboard system
* Fly command
* Discord command
* Vanish command
* Staff chat command
* Freeze command
* Freeze listener support
* Playtime command
* Settings system using `config.yml`
* Rank manager system
* Premade coloured ranks
* Rank assignment/removal through a menu
* Rank prefixes in chat
* Rank prefixes in tablist

### Planned Features

* Custom SkyMining mine system
* Mine reset system
* Shopkeeper system
* Pickaxe progression
* Economy integration
* Custom enchants
* Prestige system
* Gang/faction system
* Gang wars
* PvE and PvP progression worlds
* Reward crates
* Custom menus
* Staff moderation logs
* More configurable server systems

## Commands

| Command            | Description                      | Permission          |
| ------------------ | -------------------------------- | ------------------- |
| `/fly`             | Toggles player flight            | `gplugin.fly`       |
| `/discord`         | Displays the server Discord link | None                |
| `/vanish`          | Toggles vanish mode              | `gplugin.vanish`    |
| `/staffchat`       | Sends messages to staff chat     | `gplugin.staffchat` |
| `/freeze <player>` | Freezes a player                 | `gplugin.freeze`    |
| `/playtime`        | Displays player playtime         | None                |
| `/ranks <player>`  | Opens the rank manager menu      | `gplugin.ranks`     |

## Configuration

GPlugin uses a `config.yml` file for configurable features.

Current placeholders:

| Placeholder | Description                        |
| ----------- | ---------------------------------- |
| `{player}`  | Shows the player's username        |
| `{online}`  | Shows the number of online players |

Example:

```yml
tab:
  animated: false

  header:
    - "<yellow><bold>GPlugin</bold></yellow>"
    - "<gray>Welcome, <white>{player}</white></gray>"

  footer:
    - "<yellow>{online}</yellow> <gray>players online</gray>"
    - "<gold>play.yourserver.com</gold>"
```

## Rank System

GPlugin includes a simple rank manager designed for server staff.

Ranks are premade inside the plugin and can be assigned or removed from players using an in-game menu. Each rank has a coloured prefix that can be displayed in chat and on the tablist.

Example ranks:

* Player
* Mod
* Admin
* Owner

Example chat format:

```txt
[Admin] DiscordToS: hello
```

Rank data is stored in:

```txt
plugins/GPlugin/ranks.yml
```

## Authors

[@wxterfall](https://github.com/wxterfall) / wxterfall

## Version History

* 1.0

  * Initial core plugin setup
  * Added basic commands
  * Added staff tools
  * Added scoreboard system
  * Added configurable tablist
  * Added rank manager foundation

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

Inspiration and references:

* [README template](https://gist.github.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc)
