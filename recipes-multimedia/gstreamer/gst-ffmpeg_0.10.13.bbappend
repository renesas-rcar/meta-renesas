PRINC := "${@int(PRINC) + 1}"
EXTRA_OECONF += "--disable-silent-rules"
FFMPEG_EXTRA_CONFIGURE_COMMON_ARG += "--cross-prefix=arm-linux-gnueabihf-"
