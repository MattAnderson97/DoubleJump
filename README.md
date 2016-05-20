#Double Jump
***
Double jump is a plugin for spigot that allows players to jump higher than they normally would be able to just by pressing space twice. The height and distance that the player travels can be customised via the config.yml file.

####All releases use _java 8_

##Commands

| Command | Description |
| ------- | ----------- |
| /doublejump | reload the main configuration |
| /toggledoublejump | Enable or disable double jump. |

##Permissions

| Permission | Description |
| ---------- | ----------- |
| doublejump.jump | Basic permission.<br>Allows the player to double jump.
| doublejump.toggle | allow usage of ```/toggledoublejump``` to enable or disable double jump. |
| doublejump.reload | allow usage of ```/doublejump``` to reload the configuration. |

All ops have access to both permissions by default

##Configuration
| Setting | Description |
| ------- | ----------- |
| doublejump.height | The multiplier for how high the player gets pushed.<br>Type: double<br>Default: 1.0 |
| doublejump.length | The multiplier for how far forwards the player goes.<br>Type: double<br>Default: 1.6 |
| doublejump.cooldown | The amount of time in seconds the player has to wait between double jumps.<br>Type: integer<br>Default: 3 |
| doublejump.message | The message the player gets sent when they doublejump.<br>Type: string |
| doublejump.cooldownMessage | the message the player gets when they cannot doublejump.<br>Type: string |
