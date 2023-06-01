# Decentralised Content Negotiation Experiments

Experiments for the [DCN Project](https://github.com/YoucTagh/decentralised-cn). The [paper](https://dmkg-workshop.github.io/papers/paper9797.pdf) was presented at Data Management for Knowledge Graphs (DMKG - ESWC 2023)

### Prerequisites
* Java 9+

### Experiment Reproducibility
To reproduce the experiment, a main command line interface is displayed when running the [Main](/src/main/java/fr/minesstetienne/ci/dcn/Main.java) class, when launched the following menu is displayed: 

```
Please make a choice [0-4]:
1: Scrap and Export Data
2: Lunch the Local Experiment
3: Create an HTML output from the experiment results
4: Scrap Dump Data for visualisation
0: Quit
```

The first option starts the data collection process to be used in the experiments.
The second option uses the collected data to start the experiment process.
The third option produces a human readable version of the results.
The fourth option Scrap the Data Q[1-5000] without filtering. After this process is finished the [VisualisationDumpData](/src/main/java/fr/minesstetienne/ci/dcn/VisualisationDumpData.java) class could be used to generate a chart to visualise the data distribution. 

The final output could be found in the `data` folder. The data folder generated in our experiments are present in [this folder](/data).
