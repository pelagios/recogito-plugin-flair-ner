# Recogito Flair NER Plugin (Experimental)

An experimental Recogito NER plugin using [Flair](https://github.com/zalandoresearch/flair) by Zalando Research. 

![Screenshot](screenshot.png)

The 
plugin consists of two components:

1. A Python script that takes a filename as input, runs 
   Flair, and writes JSON results to STDOUT
2. A Scala wrapper which implements the Recogito NER API, 
  and calls out to the script through the command line

## Testing

To run __just the Python script__ use

```
python plugins/recogito.plugins.ner.flair/parse.py {filename}
```

To test parsing throught the Scala wrapper run

```
sbt test
```

## Compile and Deploy

- build the plugin using `sbt package`. This will create the 
  .jar file `target\recogito-plugin-ner-flair-0.1.jar`. 
- Copy this file into a subfolder __inside__ the Recogito 
  plugins folder
- make sure Flair is available on the Recogito server 
  (`pip install flair`)
- copy the Python script to the same subfolder
