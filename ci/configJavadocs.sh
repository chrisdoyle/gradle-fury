#!/usr/bin/env bash

if [ "$GRAPHVIZ" = "YES" ]; then
# We need GraphViz to draw figures of graphs and lattices:
  sudo apt-get install graphviz
fi