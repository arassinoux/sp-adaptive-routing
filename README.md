
# Adaptive Routing

_Alexandre Rassinoux - Semester Project - LIA EPFL_


This project presents a modular platform implemeted in Java, and capable of testing various [multi-armed bandit](https://en.wikipedia.org/wiki/Multi-armed_bandit) algorithms in parallel.
It includes the analysis of a set of stochastic, adversarial, and real-world scenarios with some reliable interpretations, by using multiple measure tools like expected reward. 

This project has been developed for the Artificial Intelligence Laboratory (LIA) at EPFL.

## Architecture
  
<img src="http://arassinoux.com/assets/architecture.png" alt="Architecture" style="width: 500px;"/>

## Project structure
<pre>
.
├── README.md
├── platform    
│   └── agents              Code of the various agent nodes including bandit algorithms
│   └── arms                Code of the various arms nodes
│   └── data                Output folder of the evaluation platform
│   └── messages            Structures of messages shared between arms, agents, and simulation nodes
│   └── simulation          Code of the simulation node
├── Plots.ipynb             Python notebook to plot some results
├── report.pdf              Report of the full project with explained results
</pre>


