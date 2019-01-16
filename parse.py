import sys
from flair.data import Sentence
from flair.models import SequenceTagger

filename = sys.argv[1]

with open(filename, 'r') as f:
  sentence = Sentence(f.read())
  tagger = SequenceTagger.load('ner')
  tagger.predict(sentence)

  json = sentence.to_dict(tag_type='ner')
  del json['text']
  
  print(json)
