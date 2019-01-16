import json
import sys
from flair.data import Sentence
from flair.models import SequenceTagger

filename = sys.argv[1]

with open(filename, 'r') as f:
  sentence = Sentence(f.read())
  tagger = SequenceTagger.load('ner')
  tagger.predict(sentence)

  result = sentence.to_dict(tag_type='ner')
  # Save space - we don't need to repeat the text in the output
  del result['text'] 
  
  print(json.dumps(result))
