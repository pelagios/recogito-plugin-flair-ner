# Recogito Flair NER Plugin (Experimental)

An experimental Recogito NER plugin using 
[Zalando Research Flair](https://github.com/zalandoresearch/flair)

The plugin has two parts

- A short Python script that takes a filename as input, runs the contents 
  through Flair, and writes entities to STDOUT as JSON
- The Scala wrapper which implements the Recogito NER API, and calls out
  to the Python script through the command line

## Testing

To run __just the Python script__ use

```
python parse.py {filename}
```

To test parsing throught the Scala wrapper run

```
sbt test
```

## Compile and Deploy

- build the plugin using `sbt package`. This will create the .jar file `target\recogito-plugin-ner-flair-0.1.jar`. Copy 
  this file into a subfolder __inside__ the Recogito plugins folder
- make sure Flair is available on the Recogito server (`pip install flair`)
- copy the Python script to the same subfolder
