# The open source SPINE Programming Reactive Code Generation platform

A completely secure and localhost approach to AI code generation on your own computer and Red Hat OpenShift AI. 
Generate a reactive, data-driven website and API in Java Vert.x intelligently by: 

1. By writing YAML rules into a Java class and method comments. 
2. Define some spine discs in your Java classes, which are `protected void` methods that start with the `_` underscore character. 
3. The Computate code generation watcher service will recognize that your Java class has changed, and automatically index your code in a Solr search engine on your own computer. 
4. The Computate code generation service will generate the code you need. 
5. Use the [Computate Developer products](https://computate.org/en-us/search/product) like the [Computate Smart Website Builder](https://computate.org/en-us/search/product?fq=pageId:computate-smart-website-builder) to build the rest of your site. 

SPINE Programming is an acronym for "SPINE Programming is not easy". 

Code that generates more code as you save. Internationalized. Integrated with Solr search. A new way to write code to make persistence easier. 


## Written by [Christopher Tate](https://www.computate.org/)

- Red Hat Principal Software Engineer in Red Hat Research 

- Creator of the Smart Village Operator and Smart Village Platform 

- Architect of the Red Hat Social Innovation Program 

- Founder of the Smarta Byar Smart Village Community FIWARE Innovation Hub 

- Principal Software Engineer for the New England Research Cloud

## Try out the AI/ML Smart Device API Code Generation yourself

Try out the computate platform yourself

You can do AI/ML Smart Device API Code Generation with computate in your own OpenShift 
cluster by following the [Smart Device API Code Generation with AI/ML hackathon course](https://github.com/smartabyar-smartvillage/smart-device-api-generation-hackathon): 

- You'll need access to a Red Hat OpenShift cluster. 
- Ask your OpenShift Admins to install OpenShift AI and the [vscode-java ImageStream here](https://github.com/nerc-images/vscode-java/blob/main/cluster-scope/base/image.openshift.io/imagestreams/vscode-java/imagestream.yaml)
- Then follow the Jupyter notebooks in this course, starting with 
  [01-install-prerequisites.ipynb](https://github.com/smartabyar-smartvillage/smart-device-api-generation-hackathon/blob/main/01-install-prerequisites.ipynb). 

For an introduction to OpenShift and the Smart Village Platform built with the 
computate platform, follow our [free course we developed to deploy a working 
Edge-to-Cloud Learning Experience with computate](https://github.com/smartabyar-smartvillage/smartabyar-smartvillage-sandbox-course). It will guide you through launching a free 
Red Hat OpenShift Developer Sandbox
environment, deploying the same FIWARE and Smart Village components as the
Smart Device API Code Generation with AI/ML hackathon course, 
and teaches how to build a new Python microservice that animates
simulated traffic data on maps over time.

