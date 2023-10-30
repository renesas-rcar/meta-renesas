# Cannot remove build path in the files, they are needed by do_package task
# Therefore, suspress this warning
INSANE_SKIP:${PN}-python += "buildpaths"

