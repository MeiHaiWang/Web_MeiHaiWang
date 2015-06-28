## Install
Need Platform for compile.

* Node.js
* NPM

## Get Start to WebContent

**Need compile to start product.**

* Node.js
```sh
$ brew update
$ brew install nodebrew
$ nodebrew install latest
$ nodebrew list
v0.12.5

current: none

$ nodebrew use v0.12.5
$ echo 'export PATH=$PATH:/Users/ユーザー名/.nodebrew/current/bin' >> ~/.bashrc
$ node -v
$ npm -v
```

* grunt

```sh
$ npm install -g grunt-cli
$ npm install
コンパイル
$ grunt
```

## watch at develop

* grunt

```sh
$ grunt watch
```

* React.js

```sh
$ jsx --watch --harmony ./WebContent/js/src/jsx ./WebContent/js/src
```