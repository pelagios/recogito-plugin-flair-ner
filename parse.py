from flair.data import Sentence
from flair.models import SequenceTagger

# TODO pick filename from CLI arg
with open('input.txt', 'r') as f:
  sentence = Sentence(f.read())
  tagger = SequenceTagger.load('ner')
  tagger.predict(sentence)

  json = sentence.to_dict(tag_type='ner')
  del json['text']
  
  print(json)
