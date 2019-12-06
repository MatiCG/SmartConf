# SmartConf

[![Build](https://github.com/MatiCG/SmartConf/workflows/Docker%20Image%20CI/badge.svg)](https://github.com/MatiCG/SmartConf/actions?workflow=Docker+Image+CI) [![Build](https://github.com/MatiCG/SmartConf/workflows/Kotlin%20CI/badge.svg)](https://github.com/MatiCG/SmartConf/actions?workflow=Kotlin+CI) [![Build](https://github.com/MatiCG/SmartConf/workflows/Python%20CI/badge.svg)](https://github.com/MatiCG/SmartConf/actions?workflow=Python+CI) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Presentation

## Working with Branches

1.  A `develop` branch is created from `master` and contains code from feature branches that has not been deployed to master
2.  Day to day development is done on `feature` branches which are created from `develop` 
3.  `Feature` branches are pushed at least daily as a backup
4.  Developers run *interactive rebase* when making pull requests
5.  A pull request is created on GitHub once code is completed on a `feature` branch
6.  A test app is made when a pull request is opened
7.  Bug fixes are committed to the same `feature` branch
8.  When pull requests are closed, the `feature` branch is merged into `develop`
9.  Once `develop` has acquired enough features for a release, it is put into a `release` branch
10.  `Release` is tested as a review app
11.  `Release` merged into `develop` and `master`


### The `master` and `develop` branches
While the `master` branch stores the official release history, and the `develop` branch serves as an integration branch for features.

As you set up your environment, start by tracking the `develop` branch locally:
```
[master] $ git checkout develop
[develop] $ git pull origin develop
```

### Day-to-day development with `feature` branches
Created by each developer, they contain work in progress code. They are merged into `develop` after peer-review using Github's pull request mechanism. The naming convention is `working-space/my-feature`. `Working-space` is either IA, App or Website

#### Feature branch | 1. Starting a feature branch
To start a feature branch, simply create a new branch from develop:
```
[develop] $ git pull origin develop
[develop] $ git checkout -b working-space/my-feature develop
```

#### Feature branch | 2. Working on a feature branch
Start working and committing locally:
```
[feature/my-feature] $ git add .
[feature/my-feature] $ git status
[feature/my-feature] $ git commit -a -m “great commit description, blah blah”
```
## Commits should not be one liners!
```
[ADD] I added this
- it adds this stuff
- Also does this
- Edited another function too
```
Ensure that you save your code on Github, at least daily:
```
[IA/my-feature] $ git push origin working-space/my-feature
```
You should regularly incorporate recent commits from develop:
```
[working-space/my-feature] $ git fetch origin
[working-space/my-feature] $ git rebase origin/develop
```

#### Feature branch | 3. Clean the history of your feature branch
We use Git's `rebase` command (with the `-i`, meaning interactive, switch) before integrating the feature branch with `master`.
For best Git history readability, make sure to **squash your commits wisely and write good commit messages**, as all your commits will ultimately be added to `develop` and `master`.
```
[working-space/my-feature] $ git checkout develop
[develop] $ git pull origin develop
[develop] $ git checkout feature/my-feature
[working-space/my-feature] $ git rebase -i develop
[working-space/my-feature] $ git checkout develop
```

If you're not yet well acquainted with the rebase command, I recommend [this chapter](https://git-scm.com/book/en/v2/Git-Tools-Rewriting-History) from the Pro Git SCM book.


#### Feature branch | 4. Peer-reviewing your code and integrating your feature back into master with Pull Requests
Prerequisites:
1. your code change is completed in your feature branch.
2. you have tested your code locally with automated tests.
3. you have verified that automated tests run successfully in your dev environment.
4. your code is pushed to origin in your feature branch.
5. you have cleaned the git history of your feature branch with a `rebase` (previous step).

Then, in Github, you can **create a  pull request** to merge your feature back into `develop`:
- Assign a developer to approve your code review.

#### Feature branch | 5. Post merge clean-up
2. Remove your feature branches:
```
[master] $ git branch -d working-space/my-feature
[master] $ git push origin :working-space/my-feature
