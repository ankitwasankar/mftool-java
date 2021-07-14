# MFTool Java
## Ready to use mutual fund library for Java apps

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.com/github/ankitwasankar/mftool-java/builds)
![MIT Licence](https://camo.githubusercontent.com/8298ac0a88a52618cd97ba4cba6f34f63dd224a22031f283b0fec41a892c82cf/68747470733a2f2f696d672e736869656c64732e696f2f707970692f6c2f73656c656e69756d2d776972652e737667)

mftool-java is a library for getting real time Mutual Funds data in India. 
It can be used in various types of projects which requires getting live quotes for a given scheme.


### Features
- Getting list of all schemes with their Scheme codes.
- Match string with mutual fund list. (useful in searching)
- Return data in Java Objects.
- There are no external dependencies.

### How to use
```
MFTool mfTool = new MFTool();
List<SchemeNameCodePair> list = mfTool.matchingSchemeName("Axis");
```