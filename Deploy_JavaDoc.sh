#!/bin/bash

# Display Repo:
echo -e "Repo Slug:"
echo -e "\tExpected:\tValkryst/VDice"
echo -e "\tActual:\t$TRAVIS_REPO_SLUG\n"

# Display JDK Version:
echo -e "JDK Version:"
echo -e "\tExpected:\toraclejdk8"
echo -e "\tActual:\t$TRAVIS_JDK_VERSION\n"

# Display 'Is Pull Request':
echo -e "Is Pull Request:"
echo -e "\tExpected:\tfalse"
echo -e "\tActual:\t$TRAVIS_PULL_REQUEST\n"

# Display Branch:
echo -e "Branch:"
echo -e "\tExpected:\tfalse"
echo -e "\tActual:\t$TRAVIS_BRANCH\n"

if [ "$TRAVIS_REPO_SLUG" == "Valkryst/VDice" ] &&
   [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] &&
   [ "$TRAVIS_PULL_REQUEST" == "false" ] &&
   [ "$TRAVIS_BRANCH" == "master" ]; then
   
    echo -e "Publishing JavaDoc...\n"

    cd $HOME
    git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/Valkryst/VDice gh-pages > /dev/null
    
    cd gh-pages
    git rm -r *
    git config user.name "Valkryst"
    git config user.email "valkryst@valkryst.com"
    cp -r /home/travis/build/Valkryst/VDice/target/site/apidocs/* .
    git add .
    git commit -m "Updates JavaDoc on successful Travis CI build. Build #$TRAVIS_BUILD_NUMBER auto-pushed to gh-pages."
    git push -fq origin gh-pages > /dev/null

    echo -e "Published JavaDoc to gh-pages branch.\n"
fi
