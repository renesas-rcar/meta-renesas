# Proprietary libraries for meta-rcar-gen3


The meta-rcar-gen3 layer of meta-renesas is supported Graphic GLES(GSX)
libraries, proprietary library of multimedia, and ICCOM software.

This README describes how to use these features and setting to local.conf.

```bash
    I/   Board configuration
    II/  Build with GLES
    III/ Build with Renesas multimedia libraries
```

There are 2 main paths:

* Please check section II to config for GLES.
* Please check section III to enable Multimedia functions.

If you would like to use Linux ICCOM driver and Linux ICCOM library, please
check section IV.

**NOTE:**

* However, to have a completed local.conf, please also refer to Build
Instruction in meta-renesas/meta-rcar-gen3/README.

* In addition, these libraries are not provided with recipes. If you would like
to use, you will need to get them from Renesas.

## I/ Board configuration


* Add this line to local.conf

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

## II/ Build with GLES


For wayland with GSX

* Please copy proprietary libraries to the directory of recipes.

* Set local.conf the following.

```bash
    # Enable Gfx Pkgs
    MACHINE_FEATURES_append = " gsx"
    MULTI_PROVIDER_WHITELIST += "virtual/libgl virtual/egl virtual/libgles1 virtual/libgles2"

    # for Wayland/Weston
    DISTRO_FEATURES_NATIVESDK_append = " wayland"
    DISTRO_FEATURES_append = " pam"
    PREFERRED_PROVIDER_virtual/libgles1 = ""
    PREFERRED_PROVIDER_virtual/libgles2 = "gles-user-module"
    PREFERRED_PROVIDER_virtual/egl = "libegl"
    PREFERRED_PROVIDER_virtual/libgl = ""
    PREFERRED_PROVIDER_virtual/mesa = ""
    PREFERRED_PROVIDER_libgbm = "libgbm"
    PREFERRED_PROVIDER_libgbm-dev = "libgbm"
    BBMASK = "mesa-gl"
```

* Run `bitbake core-image-weston`

## III/ Build with Renesas multimedia libraries


Multimedia portions depend on GLES portions.

### A/ Configuration for Multimedia features


* Please copy proprietary libraries to the directory of recipes.

* Please set local.conf the following.

```bash
    # Enable multimedia features.
    # This provides package group of plug-ins of the GStreamer, multimedia
    # libraries and kernel drivers.

        MACHINE_FEATURES_append = " multimedia"
```

### B/ Configuration for optional codecs and middleware


* Please copy proprietary libraries to the directory of recipes.

* Add features to DISTRO_FEATURES_append to local.conf

```bash
    # Additional configuration in OMX module
    " h263dec_lib"       - for OMX Media Component H263 Decoder Library
    " h264dec_lib"       - for OMX Media Component H264 Decoder Library
    " h264enc_lib"       - for OMX Media Component H.264 Encoder Library
    " h265dec_lib"       - for OMX Media Component H265 Decoder Library
    " mpeg2dec_lib"      - for OMX Media Component MPEG2 Decoder Library
    " mpeg4dec_lib"      - for OMX Media Component MPEG4 Decoder Library
    " vc1dec_lib"        - for OMX Media Component VC-1 Decoder Library
    " divxdec_lib"       - for OMX Media Component DivX Decoder Library
    " rvdec_lib"         - for OMX Media Component RealVideo Decoder Library
    " alacdec_lib"       - for OMX Media Component ALAC Decoder Library
    " flacdec_lib"       - for OMX Media Component FLAC Decoder Library
    " aaclcdec_lib"      - for OMX Media Component AAC-LC Decoder Library
    " aaclcdec_mdw"      - for AAC-LC 2ch Decoder Middleware for Linux
    " aacpv2dec_lib"     - for OMX Media Component aacPlus V2 Decoder Library
    " aacpv2dec_mdw"     - for aacPlus V2 Decoder Middleware for Linux
    " mp3dec_lib"        - for OMX Media Component MP3 Decoder Library
    " mp3dec_mdw"        - for MP3 Decoder Middleware for Linux
    " wmadec_lib"        - for OMX Media Component WMA Standard Decoder Library
    " wmadec_mdw"        - for WMA Standard Decoder Middleware for Linux
    " dddec_lib"         - for OMX Media Component Dolby(R) Digital Decoder Library
    " dddec_mdw"         - for Dolby(R) Digital Decoder Middleware for Linux
    " aaclcenc_lib"      - for OMX Media Component AAC-LC Encoder Library
    " vp8dec_lib"        - for OMX Media Component VP8 Decoder Library for Linux
    " vp8enc_lib"        - for OMX Media Component VP8 Encoder Library for Linux
    " vp9dec_lib"        - for OMX Media Component VP9 Decoder Library for Linux
    " aaclcenc_mdw"      - for AAC-LC Encoder Middleware for Linux
    " cmsbcm"            - for CMS Basic Color Management Middleware for Linux
    " cmsblc"            - for CMS CMM3 Backlight Control Middleware for Linux
    " cmsdgc"            - for CMS VSP2 Dynamic Gamma Correction Middleware for Linux
    " dtv"               - for ISDB-T DTV Software Package for Linux
    " dvd"               - for DVD Core-Middleware for Linux
    " adsp"              - for ADSP driver, ADSP interface and ADSP framework for Linux
    " avb"               - for AVB Software Package for Linux
```

Ex:
```
    DISTRO_FEATURES_append = " h264dec_lib h265dec_lib mpeg2dec_lib aaclcdec_lib aaclcdec_mdw"
```

### C/ Configuration for test packages


Must ensure that Multimedia features have been enabled.
(Please refer to III/A to enable Multimedia.)

* Please add feature to DISTRO_FEATURES_append to local.conf.

```bash
    # Configuration for multimedia test package

        DISTRO_FEATURES_append = " mm-test"
```

## IV/ Enable Linux ICCOM driver and Linux ICCOM library


For Linux ICCOM driver and Linux ICCOM library

* Please copy proprietary libraries to the directory of recipes.

* Please set local.conf the following.

```bash
    DISTRO_FEATURES_append = " iccom"
```
END.

