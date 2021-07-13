# MFTool Java
## Ready to use mutual fund library for Java apps

![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)

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