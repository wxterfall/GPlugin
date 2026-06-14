# GPlugin

A custom **SkyMining Core** plugin for a Minecraft server, built for Paper/Spigot servers.
GPlugin provides essential server features such as staff utilities, tablist styling, scoreboard support, rank management, player moderation tools, and configurable server branding.

This plugin is designed as the core foundation for a custom SkyMining server, with future support for mines, ranks, progression, gangs, rewards, and server-specific gameplay systems.

---

## Features

### Core Server Features

* Custom tablist header and footer
* Configurable animated/static tablist
* Custom scoreboard system
* Player playtime tracking
* Discord command
* Fly command
* Vanish command
* Staff chat command
* Freeze command for moderation
* Freeze logout handling

### Rank System

* Premade server ranks
* Coloured rank prefixes
* Rank assignment/removal menu
* Rank prefixes shown in chat
* Rank prefixes shown in tablist
* Saved player ranks

### Staff Tools

* `/freeze` to freeze players
* `/vanish` for staff invisibility
* `/staffchat` for private staff communication
* `/fly` for staff movement/admin use

---

## Commands

| Command            | Description                | Permission          |
| ------------------ | -------------------------- | ------------------- |
| `/fly`             | Toggle flight              | `gplugin.fly`       |
| `/discord`         | Shows the server Discord   | None                |
| `/vanish`          | Toggle vanish mode         | `gplugin.vanish`    |
| `/staffchat`       | Talk in staff chat         | `gplugin.staffchat` |
| `/freeze <player>` | Freeze a player            | `gplugin.freeze`    |
| `/playtime`        | View playtime              | None                |
| `/ranks <player>`  | Open the rank manager menu | `gplugin.ranks`     |

---

## Configuration

The main configuration file is located at:

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

### Placeholders

| Placeholder | Description         |
| ----------- | ------------------- |
| `{player}`  | Player name         |
| `{online}`  | Online player count |

---

## Rank System

GPlugin includes a basic rank manager with premade ranks such as:

* Player
* Mod
* Admin
* Owner

Ranks are coloured and can be displayed as prefixes in chat and tablist.

Example:

```txt
[Admin] DiscordToS: hello
```

Ranks are stored inside:

```txt
plugins/GPlugin/ranks.yml
```

---

## Installation

1. Download or build the plugin jar.
2. Place the jar inside your server's `plugins` folder.
3. Start the server.
4. Edit the generated config files in:

```txt
plugins/GPlugin/
```

5. Restart the server.

---

## Building from Source

Clone the repository:

```bash
git clone https://github.com/wxterfall/GPlugin.git
```

Open the project in chosen IDE.

If using Maven:

```bash
mvn clean package
```

The compiled jar will be located in:

```txt
target/
```

Copy the jar into your Minecraft server's `plugins` folder.

---

## Planned Features

GPlugin is being developed as a full SkyMining server core. Planned features include:

* Custom mine system
* Mine reset system
* Shopkeeper system
* Pickaxe progression
* Economy integration
* Custom enchants
* Player prestige system
* Gang/faction system
* Gang wars
* PvE and PvP progression worlds
* Rewards and crates
* Custom menus
* More rank permissions
* Staff moderation logs

---

## Requirements

* Java 17+
* Paper/Spigot 1.20.4 (Compatibility with other versions not tested, likely supported however.)
* Minecraft server with plugin support

---

## Author

Developed by **wxterfall**.

