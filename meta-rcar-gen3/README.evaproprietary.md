# R-Car Gen3 Evaluation Software Package for Linux


The meta-rcar-gen3 layer of meta-renesas supports the Click-through licensed
Linux Drivers and Gfx/MMP packages.

This README describes how to use these features and setting local.conf.

```bash
    I/   Build configuration
    II/  Obtain and Install Renesas Graphics Drivers
```

**NOTE:**

* However, to have a completed local.conf, please also refer to Build
Instruction in [meta-renesas/meta-rcar-gen3/README.md](README.md).

* In addition, these libraries are not provided with recipes. If you would like
to use, you will need to get them from Renesas.

## I/ Build configuration


* Add the target board to local.conf

    * For Salvator-X board

    ```bash
       MACHINE = "salvator-x"
    ```

    * For R-Car Starter Kit Premier(H3ULCB) board

    ```bash
       MACHINE = "h3ulcb"
    ```

    * For R-Car Starter Kit Pro(M3ULCB) board

    ```bash
       MACHINE = "m3ulcb"
    ```

    * For R-Car Starter Kit Pro(M3NULCB) board

    ```bash
       MACHINE = "m3nulcb"
    ```

    * For Ebisu board

    ```bash
       MACHINE = "ebisu"
    ```

* Set SOC family name

    * For H3: r8a7795

    ```bash
       SOC_FAMILY = "r8a7795"
    ```

    * For M3: r8a7796

    ```bash
       SOC_FAMILY = "r8a7796"
    ```

    * For M3N: r8a77965

    ```bash
       SOC_FAMILY = "r8a77965"
    ```

    * For E3: r8a77990

    ```bash
        # Already added in machine config: ebisu.conf
        SOC_FAMILY = "r8a77990"
    ```

* When using the click-through version of the gfx/mmp packages, you need to add
the following to your local.conf

```bash
   DISTRO_FEATURES_append = " use_eva_pkg"
```

## II/ Obtain and Install Renesas Graphics Drivers


Before setting up the build environment, you need to download the proprietary
drivers.

* Download Renesas graphic drivers with a "click through" license from
[Renesas website][rcar Linux Drivers] and unzip them into a folder.

**NOTE:**

* You have to register with a free account on MyRenesas and accept the license
conditions before downloading the drivers.
The operation is fast and simple nevertheless mandatory to access evaluation of
non open-source drivers for free.
Once you registered, you can download two zip files.

```bash
$ cd <folder containing the two zip files>
$ unzip -o R-Car_Gen3_Series_Evaluation_Software_Package_for_Linux-*.zip
$ unzip -o R-Car_Gen3_Series_Evaluation_Software_Package_of_Linux_Drivers-*.zip
```

To install them into the correct place in the Yocto BSP, a copy script is used.

```bash
$ cd ./meta-renesas
$ sh meta-rcar-gen3/docs/sample/copyscript/copy_evaproprietary_softwares.sh <path to the folder containing the packages>
```

[rcar Linux Drivers]: https://www.renesas.com/us/en/solutions/automotive/rcar-download/rcar-demoboard-2.html
