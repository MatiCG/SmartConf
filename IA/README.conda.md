# Welcome to the anaconda tutorial

1.  What is Anaconda

    Anaconda is created by Continuum Analytics, and it is a Python distribution that comes preinstalled with lots of useful python libraries for data science.
    Anaconda is popular because it brings many of the tools used in data science and machine learning with just one install, so it’s great for having short and simple setup.
    Anaconda uses the concept of creating environments so as to isolate different libraries and versions. Anaconda also introduces its own package manager, called conda, from where you can install libraries.
****    
2.  How to install Anaconda

    To install anaconda go [here](https://www.anaconda.com/distribution/)
    then execute the downloaded file
****
3.  Setup your anaconda environment with conda


    -  to create an environment: 
    
    >  conda create --name "name" "as many packages as you want"

    -  activate it:
    
    >  `conda activate "name"
    
    -  desactivate conda:
    
    >  conda desactivate
    
    -  desactivate environment:

    >  conda desactivate "name"
    
    -  see all env:

    >  conda info --envs
    
    -  to install a package on a conda env:

    >  conda search "name"
    
    
    >  conda install "name"
    
    -  to see all installed packages:
    
    >  conda list
    
    -  remove a package from an environment:
    
    >  conda remove --name "env_name" "package_name"
    
    -  remove an environment:
    
    >  conda remove --name "env_name" --all

****
4.  Useful links
    
    *  [Understand anaconda and learn some conda commands](https://www.google.com/search?client=firefox-b-d&q=python+anaconda+tutorial#kpvalbx=_xF2EXribDMPeavvnqIAH31)
    *  [Install tensorflow](https://machinelearningmastery.com/setup-python-environment-machine-learning-deep-learning-anaconda/)
