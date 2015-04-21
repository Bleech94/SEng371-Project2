# SEng371-Project2

# Introduction

This project focuses on the Apache-Ant project. The reason we chose this project is because it has both Github data, and publicly available email archives in the form of mbox files. This same project will be able to be run on all other projects which also have these two resources available.

In this project the goal is to, based on the communications that occur, infer the structure of the company. Connecting this to the commits that occur, with other data in the Github system for the project, we should be able to see who the people are that make the largest impact on the code, who is at the center of communications, and determine if the people who we would expect to be at the helm of a project are represented in the data.

# Project Question

Our formal project question is as follows: “Can we determine the hierarchical organization and the social structure within an organization from looking at the codebase and internal communication?”

An example of a situationally specific question is: “Can we create some graphical representation of internal communication that accurately respresents the structure found on the companies website?”

# Hypotheses

* We can determine the organizational structure.
* We can discover major contributors by mapping communications with a visual representation. 
* We can estimate the development methodologies that Apache uses.
 

##Importance
This area of research can reveal a lot about organizational structure, social structure, the effectiveness of different communication styles, and possibly provide credit (or bonuses) to those who have empirically contributed the most to a project’s success. Developers do not often recognize the amount of communication that it takes to properly develop a product, so perhaps this will help a team understand that although a manager may not provide that many lines of code, they still play an integral part in coordinating people.

##Codebases/Systems Analyzed
Apache Ant is the only project that we ran our methodology on, but all open source Apache projects include the same structure and have the same resources available to the public, so there is a massive number of projects that this could be done with from just them. 

##Metrics
Our metrics are selected to provide maximum insight into the interactions of the developers. We also collect the contributions of developers to code. 
* Number of conversations each employee is involved in: Might give us an idea of who the project managers,senior developers, or team leads are.
* Number of commits per developer over time. This combined with the first point could show us which contributors are more technical and which are more in the management or design side of the organization.
* Number of conversations pairs of people have in common: Knowing this could allow us to construct some kind of conversation tree. This could help us determine who the project managers are, who the team leaders are, and could give us an idea of who works with who.
* Knowing who codes together could help us determine the team structure within an organization.

##Leveraged Tools
Our data creation process involves a few major stages that rely on existing toolsets:
* Gource: A tool which looks at the file structure of a repository over time and connects changes to the structure to the developers. This renders a video of a user defined time period of interest. 
* WGET: A linux command line tool that lets you download the contents from a URL.
* Python: A programming language used for conversion of mbox files to csv files.
* Java: A programming language used for parsing the csv files into useable data. 

# Milestones

|Task | Date | Weight (out of 5)|
|-----|------|--------|
|Finalize communications mapping code | March 20 | 4 |
|Run code on monthly data sets | March 24 | 2 |
|Combine monthly results into full project data set| March 25 | 1 |
|Analyze results | March 27 | 2 |
|Create Visuals | April 7 | 5 | 
|Final Report | April 17 | 5 |

##Roles of Team Members:
Brandon Leech and Jorin Weatherston shared the workload evenly and took part in the same tasks.

##Our Tool 

##Results

##Conclusion



##References
