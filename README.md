# OpenEmbedded/Yocto BSP layer for Renesas Electronics's SoCs

This layer provides support for Renesas Electronics's platforms for use with
OpenEmbedded and/or Yocto.

Layer maintainer(s):

* Please see the MAINTAINERS file for more details.

## Supported Boards/Machines


In addition, this also provides layer which supports boards using Renesas SoCs.
Layer and boards that support is the following:

    meta-rcar-gen3
        - Renesas Electronics Corporation. R-Car Salvator-X (R8A77950/R8A77951/R8A77960/R8A77961/R8A77965)
        - Renesas Electronics Corporation. R-Car Starter Kit premier(H3ULCB) (R8A77950/R8A77951)
        - Renesas Electronics Corporation. R-Car Starter Kit pro(M3ULCB) (R8A77960/R8A77961)
        - Renesas Electronics Corporation. R-Car Starter Kit pro(M3NULCB) (R8A77965)
        - Renesas Electronics Corporation. R-Car Ebisu (R8A77990)
        - Renesas Electronics Corporation. R-Car Draak (R8A77995)

Please see README in the layer directory for the description of the board.

## Original BSP image

The meta-renesas layer provides original BSP image. The following:

* core-image-minimal
* core-image-weston
