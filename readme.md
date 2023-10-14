# MoneyPit

Economy service plugin implementing [MoneyPitAPI].

IMPORTANT: THIS IS NOT COMPATIBLE WITH VAULT. Economy plugins wanting to use this economy API must register
[MoneyPitAPI] as a service provider similar to vault.


This plugin is a simple hook point for other plugins that use the [MoneyPitAPI] to implement an economy system. It
**does not provide economy support by itself**, you need another plugin to implement the API.

## Commands

### `/moneypit`

This command will display plugin and api versions, along with currently registered plugins providing economy
implementations. Requires `moneypit.command` permission (granted to OPs by default).

[MoneyPitAPI]: https://github.com/YouHaveTrouble/MoneyPitAPI
