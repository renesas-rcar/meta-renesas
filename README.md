# OpenEmbedded/Yocto BSP layer for Renesas Electronics's SoCs

This layer provides support for Renesas Electronics's platforms for use with
OpenEmbedded and/or Yocto.

Layer maintainer(s):

* Please see the MAINTAINERS file for more details.

## Supported Boards/Machines


In addition, this also provides layer which supports boards using Renesas SoCs.
Layers and boards that support is the following:

    meta-rcar-gen3
        - Renesas Electronics Corporation. Salvator-X (R8A7795/R8A7796/R8A77965)
        - Renesas Electronics Corporation. R-Car Starter Kit premier(H3ULCB) (R8A7795)
        - Renesas Electronics Corporation. R-Car Starter Kit pro(M3ULCB) (R8A7796)
        - Renesas Electronics Corporation. R-Car Starter Kit pro(M3NULCB) (R8A77965)
        - Renesas Electronics Corporation. R-Car Ebisu (R8A77990)

    meta-rcar-bsp, meta-rcar-adas
        - Renesas Electronics Corporation. R-Car Falcon (R8A779A0)
        - Renesas Electronics Corporation. Condor (R8A77980)
        - Renesas Electronics Corporation. Eagle (R8A77970)

Please see README in the layer directory for the description of the board.

## Original BSP image

The meta-rcar-gen3 layer provides original BSP image. The following:

* core-image-minimal
* core-image-weston

The meta-rcar-bsp, meta-rcar-adas layers provide original BSP image. The following:

* rcar-image-minimal
* rcar-image-adas
