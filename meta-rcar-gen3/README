meta-rcar-gen3
==============

This layer provides that evaluation board is mounted ARM SoCs of Renesas
Electronics, called the R-Car Generation 3. Currently, this supports
board and the SoCs of the following:
     - Board: Salvator-X / SoC: R8A7795 (R-Car H3), R8A7796 (R-Car M3), R8A77965 (R-Car M3N)
     - Board: R-Car Starter Kit premier(H3ULCB) / SoC: R8A7795 (R-Car H3)
     - Board: R-Car Starter Kit pro(M3ULCB) / SoC: R8A7796 (R-Car M3)
     - Board: Ebisu / SoC: R8A77990 (R-Car E3)

Patches
=======

Please submit any patches for this layer to: takamitsu.honda.pv@renesas.com
Please see the MAINTAINERS file for more details.

Dependencies
============

This layer depends on:

    URI: git://git.yoctoproject.org/poky
    layers: meta, meta-yocto, meta-yocto-bsp
    branch: rocko

    URI: git://github.com/renesas-rcar/meta-renesas
    layers: meta-rcar-gen3
    branch: rocko

    URI: git://git.linaro.org/openembedded/meta-linaro.git
    layers: meta-optee
    branch: rocko

    URI: git://git.openembedded.org/meta-openembedded
    layers: meta-oe
    branch: rocko

Build Instructions
==================

The following instructions require a Poky installation (or equivalent).

Initialize a build using the 'oe-init-build-env' script in Poky. e.g.:

    $ source poky/oe-init-build-env

After that, initialized configure bblayers.conf by adding meta-rcar-gen3 layer. e.g.:

    BBLAYERS ?= " \
        <path to layer>/poky/meta \
        <path to layer>/poky/meta-yocto \
        <path to layer>/poky/meta-yocto-bsp \
        <path to layer>/meta-renesas/meta-rcar-gen3 \
        <path to layer>/meta-linaro/meta-optee \
        <path to layer>/meta-openembedded/meta-oe \
    "

To build a specific target BSP, configure the associated machine in local.conf:

    MACHINE ??= "<supported board name>"

Select the SOC

    For H3: r8a7795
    SOC_FAMILY = "r8a7795"

    For M3: r8a7796
    SOC_FAMILY = "r8a7796"

    For M3N: r8a77965
    SOC_FAMILY = "r8a77965"

    For E3: r8a77990
    SOC_FAMILY = "r8a77990"
    Already added in machine config: ebisu.conf

Configure for systemd init in local.conf:

    DISTRO_FEATURES_append = " systemd"
    VIRTUAL-RUNTIME_init_manager = "systemd"

Configure for ivi-shell and ivi-extension

    DISTRO_FEATURES_append = " ivi-shell"

Configure for USB 3.0

    MACHINE_FEATURES_append = " usb3"

Enable tuning support for Capacity Aware migration Strategy (CAS)

    MACHINE_FEATURES_append = " cas"

Build the target file system image using bitbake:

    $ bitbake core-image-minimal

After completing the images for the target machine will be available in the output
directory 'tmp/deploy/images/<supported board name>'.

Images generated:
    * Image (generic Linux Kernel binary image file)
    * Image-<machine name>.dtb (DTB for target machine)
    * core-image-minimal-<machine name>.tar.bz2 (rootfs tar+bzip2)
    * core-image-minimal-<machine name>.ext4  (rootfs ext4 format)

Build Instructions for SDK
==========================
This may be changed in the near feature. These instructions are tentative.

Should define the staticdev in SDK image feature for installing the static libs
to SDK in local.conf.

    SDKIMAGE_FEATURES_append = " staticdev-pkgs"

Use bitbake -c populate_sdk for generating the toolchain SDK:
For 64-bit target SDK (aarch64):

    $ bitbake core-image-minimal -c populate_sdk

The SDK can be found in the output directory 'tmp/deploy/sdk'
    * poky-glibc-x86_64-core-image-minimal-aarch64-toolchain-x.x.sh

Usage of toolchain SDK:
Install the SDK to the default: /opt/poky/x.x
For 64-bit target SDK:

    $ sh poky-glibc-x86_64-core-image-minimal-aarch64-toolchain-x.x.sh

For 64-bit application use environment script in /opt/poky/x.x

    $ source /opt/poky/x.x/environment-setup-aarch64-poky-linux

ULCB Information
================
Refer to the following for more information of ULCB:

    http://elinux.org/R-Car

The information on building and running Yocto on R-Car Generation 3
=========================
Refer to the following for more information:

    https://elinux.org/R-Car/Boards/Yocto-Gen3
