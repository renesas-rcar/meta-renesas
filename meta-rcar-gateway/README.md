This README file contains information on the contents of the meta-rcar-gateway layer.

Please see the corresponding sections below for details.

Dependencies
============

  URI: git://git.yoctoproject.org/poky
  branch: kirkstone

  URI: git://git.openembedded.org/meta-openembedded
  branch: kirkstone

Patches
=======

* Please submit any patches for this layer to: rcar-yocto@lm.renesas.com

* Please see the MAINTAINERS file for more details.

Table of Contents
=================

  I. Adding meta-rcar-gateway layer to your build
 II. Build instruction


I. Adding meta-rcar-gateway layer to your build
=================================================

Run 'bitbake-layers add-layer meta-rcar-gateway'

II. Build instruction
========

The following instructions require a Poky installation (or equivalent).

* This also needs git user name and email defined:

```bash
   $ git config --global user.email "you@example.com"
   $ git config --global user.name "Your Name"
```

* Initialize a build using the 'oe-init-build-env' script in Poky. e.g.:

```bash
    $ source poky/oe-init-build-env
```

* After that, initialized configure bblayers.conf by adding meta-rcar-gateway layer.
e.g.:

```bash
    BBLAYERS ?= " \
        <path to layer>/poky/meta \
        <path to layer>/poky/meta-poky \
        <path to layer>/poky/meta-yocto-bsp \
        <path to layer>/meta-renesas/meta-rcar-gateway \
        <path to layer>/meta-openembedded/meta-python \
        <path to layer>/meta-openembedded/meta-oe \
    "
```

* To build a specific target BSP, configure the associated machine in local.conf:

```bash
	MACHINE ??= "<supported board name>"
```

Board|MACHINE
-----|-------
Spider|MACHINE = "spider"
Starter Kit S4|MACHINE = "s4sk-proto"

s4sk-proto is for the prototype version of S4 Starter Kit board, this machine's name will be replaced after official version of S4 Starter Kit board released.

```

* For a sample local.conf file, please refer to: [docs/sample/conf/](docs/sample/conf/)

* Build the target file system image using bitbake:

```bash
    $ bitbake rcar-image-minimal
```

After completing the images for the target machine will be available in the
output directory 'tmp/deploy/images/<supported board name>'.

Images generated:

* Image (generic Linux Kernel binary image file)

* \<SoC\>-\<machine name\>.dtb (DTB for target machine)

* rcar-image-minimal-\<machine name\>.tar.bz2 (rootfs tar+bzip2)

