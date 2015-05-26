# blacklist-fuzziness-detector
A famous implementation of QUALI-T's [fuzziness detector API](https://github.com/team-qualit/fuzziness-detector-api). Blacklist Fuzziness Detector has (you can imagine) a list of blacklisted words. Each of these words have a list of suggestions with better words.

## Example
You can extend the list of blacklisted words by opening the file `blacklisted-words.csv`. Each line represents a blacklisted word and suggestions separated by a `;` (semicolon). 

For example:

`good;Good is not measurable, please use a specific value;`

`badword;This is a explaination why 'badword' is bad; This could be a second explanation or a suggestion to improve the 'badword'`

## Credits
Corina Honegger (c1honegg@hsr.ch), Emre Avsar (emre@avsar.ch)