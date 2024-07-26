## This extention is an updated version of [Lor's add-custom-header burp extention](https://github.com/PortSwigger/add-custom-header/tree/master) using burps new Montoya api and adding persistent settings, adding header adding/replacing logic, adding multiple tabs to handle multpile and adding regex to allow for request information to infulence whether to add/replace headers.

## Installation
* Download the jar file from [Here](https://github.com/THawley42/BetterHeader/releases).
* Add it to Burp Suite using the `Extensions` tab
## Usage
There are 2 ways to use each BetterHeader tab (Note: If your tabs need the same scope, you can use a single session handling rule and just add multiple rule acctions under it.)
# Basic (ie hardcoded values)
* Select the `BetterHeader` tab, select the `Hard-Coded Value` button and set up the header name, prefix and hardcoded value text boxes
* Click the `Update Header` button and confirm the header shows up corectly
* Then open burps settings and select `Sessions`
* Add a `Session`, then add a `Rule action` and select `Invoke a Burp extension`
* Select the `Use for Better Header (Tab #)` option in the `Extention action handler` drop down
* Click `OK`, set the Session handling rule's scope, then click `OK` again
# For using with dynamic values (ie the regex option)
* Record a macro that has the value you want in one of the responses
* Select the `BetterHeader` tab, select the `Regular Expression` button and set up the header name, prefix and Regex text boxes
* Click the `Update Header` button and confirm the header shows up corectly
* Then open burps settings and select `Sessions`
* Add a `Session`, then add a `Rule action` and select `Run a macro`
* select your macro, check the `After running the macro, invoke a Burp extention action handler` box and select the `Use for Better Header (Tab #)` option in the drop down
* Click `OK`, set the Session handling rule's scope, then click `OK` again
