# SEng371-Project2

# Introduction

This project focuses on the Apache-Ant project. The reason we chose this project is because it has both Github data, and publicly available email archives in the form of mbox files. This same project will be able to be run on all other projects which also have these two resources available.

In this project the goal is to, based on the communications that occur, infer the structure of the company. Connecting this to the commits that occur, with other data in the Github system for the project, we should be able to see who the people are that make the largest impact on the code, who is at the center of communications, and determine if the people who we would expect to be at the helm of a project are represented in the data.

It is interesting to note that for the Apache projects there are no social hierachies posted publicly, only lists of contributors. This means that you cannot infer the social structure of the project from the information available. This is true for many open source projects that match the "flat" social heirachy style. 

We beleive, and want to be able to determine the true social structure of the project despite the flat heirarchy that they have posted. 

# Project Question

Our formal project question is as follows: “Can we determine the hierarchical organization and the social structure within an organization from looking at the codebase and internal communication?”

An example of a situationally specific question is: “Can we create some graphical representation of internal communication that accurately respresents the structure found on the companies website?”.

We want to be able use the tools we develop to predict what the actual social structure is. It seems impossible for the structure to actually be flat, and with our tools we want to be able to say that, for example, this individual is a major project leader based on the amount of communications being sent to and from them, and the code submissions that they perform, or that this person serves a purely communicative or coding function. 


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

##Our Tools 
We now have two seperate tool chains we have developed; one for assessing the number of conversations that a person initiates, and one for seeing who is speaking to who and how much. Our tool chain starts with the same components for data preprocessing. With these tools we hope to be able to give definitive structure to the publicly flat hierarchy of these open source projects, and generate visual representations that best present our findings. 

We also used github statistics to understand individuals developer roles, and the public contributors lists from apache to connect our findings with the official list of individuals working on Ant. 

###Email Communications Processors
* First process: The collection of mbox files from the apache project website. Because our project is based in linux we are using wget to download all of the resources on a webpage. We then unzipped all of the files to a location.

wget -r -l 0 http://ant.apache.org/mail/dev/

* Second Process: Using Python we used reg-expressions to parse through all of the entries in the data set. This preprocessing readies the content for useage in either of the two different java programs that we wrote. 

mboxToCsvConverter.py

* Third Process A: Project 1: Amount of conversations started by individuals. In this program we have the preprocessed content being analyzed to determine the number of conversations that an individual has started. From this we can understand and predict who the major contributors of different projects are. The output is a csv with {name,numConvs} as entries. 

conversationCounter.java

* Third Process B: Project 2: Social structure graphs. In this program we have the processed content being analyzed to see who is speaking to who. This helps us make predictions about who is socially dominant or prevelant in the developement process. 

conversationPairCounter.java

* Fourth Process: Graphing our results. This step was applied to the csv files from projects 1 and 2. In this step we opted to utilize LibreOffice Calc bar graphs for presenting our results, and the manual creation of a node-link heirarchy graph. Our reasoning is that is easier to rank both the number of conversations that an individual has started compared to others using bar graphs, and it is easier to visually compare communications between pairs of individuals when represented as bar graphs. We then use the Node-link graph to present a finalized social heirarchy. 

### Github Contributions
Because the Apache projects have been ported to github there are statistics on contributions per person available. We can then support or compare the code contributions of an individual with our communications based predictions to futher illuminate the role that they play. We collected graphs on the contributions of the most active github contributors. 

### Public participants list
A list of particiants is available at the Apache Ant website. This list is not ordered, and is posted below. The categories that a person can belong to are Project Management Committee Active or Emeritus Member, and Committers Active or Emeritus. We will ignore all Emeritus members in our analysis. 

|Project Management Committee Active Members|
|-------------------------------------------|
|Bruce Atherton|
|Stephane Bailliez|
|Matt Benson|
|Stefan Bodewig|
|Dominique Devienne|
|Erik Hatcher|
|Martijn (J.M.) Kruithof|
|Antoine Levy-Lambert| 
Steve Loughran|
Conor MacNeill| 
Jan Matèrne| 
Peter Reilly|
Sam Ruby|
Magesh Umasankar|
Christoph Wilhelms|
Kevin Jackson|
Jesse Glick|
Jean-Louis Boudart|


|Committers Active Members|
|-------------------------|
|Steve Cohen|
|Charles Duffy|
|Jose Alberto Fernandez|
|Jon Schneider|
|Alexey Solofnenko|

As you can see, there is no aparent structure to this listing, entries are not even alphebetized. We would like to be able to provide insight into how this flat structure is organize in actuality, with individuals who are important highlighted for their contributions. 

##Results
We first present our first project's results. The idea with our first project was to get a general idea of the social structure of the Ant project. We did this by determining the number of unique people communicating in the mailing lists, and the number of conversations that they each started. In this graph we have the number of conversations going vertically, with only people who were started more than 10 unique conversations listed on the x axis. 

####Project 1 Results
Here we list some statistics.
* Number of Unique Contributors: 2607
* Number of Unique Contributors with more than >10 Communications: ~350

From this we can see that there is a massive number of minorly contributing individuals, and an small group of product owners who communicate outwardly very frequently. The massive number of minor contributors is something that we would expect from an open source project, and not from closed source projects, and tells us that the project is infact at least recieving input from the general public/general developer fields. The graph acomplishes its goal of generally giving insight into the communication structure of the Ant project and definitely shows us there are elite product owners. In subsequent sections we will illuminate who those people are. 

![alt tag](http://i.imgur.com/pBFv1SB.png)
#####Fig: Number of Messages sent per Person



![alt tag](http://i.imgur.com/4gQp2vD.jpg)
#####Fig: Conversations between pairs of people

There are so many contributors and yet most commits are from the same person, and the top 4 have committed 99%. This makes sense in an open source project because all changes have to be approved by a product owner in order to maintain organization. It appears that Stefan Bodewig makes the majority of changes, and realityforge (Peter Donald) makes the major changes or perhaps focuses on refactoring since he has the most additions and deletions.

![alt tag](http://i.imgur.com/mATG1ah.png) 
#####Fig: Number of Commits per person over Time 

####Node-Link Graph 
Based on our research we've been able to estimate a structure of the organization. There are tons of ocntributors, but the top few are doing the majority of the talking. Based on this we've determined that the contributors can be seperated into 3 tiers. The first tier, as shown in red in the diagram, play the product owner role. They play a large role in commiting code as well as coordinating the overall project by participating in a lot of conversations, many of which are with other top contributors. The second tier, yellow, are the most active developers. These guys are speaking with the product owners the most and have likely been involved in more than one aspect of the Ant project. The third and largest tier, green, are the minor contributors. There are thousands of developers who would fit into this tier. They typically only speak with one product owner and have likely focused on one particular aspect of the Ant project. Very little communication is done from a yellow/green contributor to a yellow/green contributor, it is mostly between red and yellow or red and green.

![alt tag](http://i.imgur.com/QKCkKWd.jpg)
#####Fig: Derived Node Graph Structure

##Conclusion

##Future Work
With our tools we hope to be able to provide insight into the social structure of open source projects generally, but it seems like we could also look at specific examples of successful or failed projects, and compare and contrast the two to possibly answer the questions of "why did it fail" and "who's fault is it" and "what social structures and dynamics are key to success and failure". 

##References
* http://mail-archives.apache.org/mod_mbox/ant-dev/
* https://github.com/apache/ant/graphs/contributors?from=2001-12-18&to=2002-02-01&type=c
* http://ant.apache.org/contributors.html
