# meta-rcar-adas

This README file contains information on the contents of the
meta-rcar-adas layer which provides ADAS packages needed
by Renesas SoCs.

Please see the corresponding sections below for details.


## Dependencies


This layer depends on:

```
  URI: git://git.yoctoproject.org/poky
  layers: meta, meta-poky, meta-yocto-bsp
  branch: dunfell
```
```
  URI: git://git.openembedded.org/meta-openembedded
  layers: meta-oe, meta-python
  branch: dunfell
```
```
  URI: https://github.com/renesas-rcar/meta-renesas.git
  layers: meta-rcar-bsp
  branch: dunfell
```

## Contribution


* Please submit any patches for this layer to: rcar-yocto@lm.renesas.com

* Please see the MAINTAINERS file for more details.


## Build Instructions


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

In order to use this layer, you need to make the build system aware of
it.

Assuming the meta-rcar-adas layer exists at the top-level of your
yocto build tree, you can add it to the build system by adding the
location of the meta-rcar-adas layer to bblayers.conf, along with any
other layers needed.
e.g.:

```
  BBLAYERS ?= " \
    <path to layer>/meta \
    <path to layer>/meta-poky \
    <path to layer>/meta-yocto-bsp \
    <path to layer>/meta-renesas/meta-rcar-bsp \
    <path to layer>/meta-renesas/meta-rcar-adas \
    <path to layer>/meta-openembedded/meta-python \
    <path to layer>/meta-openembedded/meta-oe \
    "
```

* To build a specific target BSP, configure the associated machine in local.conf:

```
    MACHINE ??= "<supported board name>"
```

Board|MACHINE
-----|-------
Falcon|MACHINE = "falcon"
Condor|MACHINE = "condor"
Eagle|MACHINE = "eagle"

* Configure for systemd init in local.conf:

```
    DISTRO_FEATURES_append = " systemd"
    VIRTUAL-RUNTIME_init_manager = "systemd"
```

* For a list of sample local.conf file, please refer to: [docs/sample/conf/](meta-rcar-bsp/docs/sample/conf/)

* Build the target file system image using bitbake:

```bash
    $ bitbake rcar-image-adas
```

After completing the images for the target machine will be available in the
output directory 'tmp/deploy/images/<supported board name>'.

Images generated:

* Image (generic Linux Kernel binary image file)

* \<SoC\>-\<machine name\>.dtb (DTB for target machine)

* rcar-image-adas-\<machine name\>.tar.bz2 (rootfs tar+bzip2)

* rcar-image-adas-\<machine name\>.ext4  (rootfs ext4 format)


## Build Instructions for SDK


NOTE:

**This may be changed in the near feature. These instructions are tentative.**

Should define the staticdev in SDK image feature for installing the static libs
to SDK in local.conf.

```
    SDKIMAGE_FEATURES_append = " staticdev-pkgs"
```

### For 64-bit target SDK (aarch64)


Use `bitbake -c populate_sdk` for generating the toolchain SDK

```bash
    $ bitbake rcar-image-adas -c populate_sdk
```

The SDK can be found in the output directory `tmp/deploy/sdk`

* `poky-glibc-x86_64-rcar-image-adas-aarch64-<machine name>-toolchain-x.x.sh`

### Usage of toolchain SDK


Install the SDK to the default: `/opt/poky/x.x`

* For 64-bit target SDK

```bash
    $ sh poky-glibc-x86_64-rcar-image-adas-aarch64-<machine name>-toolchain-x.x.sh
```

* For 64-bit application, using environment script in `/opt/poky/x.x`

```bash
    $ source /opt/poky/x.x/environment-setup-aarch64-poky-linux
```
