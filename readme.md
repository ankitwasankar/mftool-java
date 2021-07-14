# MFTool Java
## Ready to use mutual fund library for Java apps

[![Build Status](https://travis-ci.com/ankitwasankar/mftool-java.svg?branch=master)](https://travis-ci.com/github/ankitwasankar/mftool-java)
![MIT Licence](https://camo.githubusercontent.com/8298ac0a88a52618cd97ba4cba6f34f63dd224a22031f283b0fec41a892c82cf/68747470733a2f2f696d672e736869656c64732e696f2f707970692f6c2f73656c656e69756d2d776972652e737667)

mftool-java is a library for getting Mutual Funds data in India in Java applications. 
It can be used in various types of projects which requires getting live quotes for a given scheme.

###### Note: This library requires internet connection to fetch Mutual Fund data.

### Features
- Fetch list of all the mutual fund schemes with their scheme-codes.
- Match string with names of mutual fund list. (useful in auto-suggestion in searches)
- Returns data in Java Objects.

### How to use
```
// Return the List of mutual funds which matches keyword "Axis"
MFTool mfTool = new MFTool();
List<SchemeNameCodePair> list = mfTool.matchingSchemeName("Axis");


// Retuns List of all the mutual fund list in India
MFTool tool = new MFTool();
List<SchemeNameCodePair> list = tool.allSchemes();
```