fakeroot python do_extra_symlinks() {
    base_targetsys = d.getVar('BASETARGET_SYS', True)
    targetsys = d.getVar('TARGET_SYS', True)

    if base_targetsys != targetsys:
        dest = d.getVar('D', True) + d.getVar('libdir', True) + '/' + base_targetsys
        dest_list = [dest]
        # For multilib like aarch64 + arm, need 2 symlinks:
        # 1) BASETARGET_SYS as usual
        # 2) BASETARGET_SYS + "-gnueabi" for multilib
        libce = d.getVar('LIBCEXTENSION', True)
        abie = d.getVar('ABIEXTENSION', True)
        if abie and libce and targetsys.endswith(libce + abie):
            dest_list.append(dest + libce + abie)
        src = targetsys
        for dir in dest_list:
            if not os.path.lexists(dir) and os.path.lexists(d.getVar('D', True) + d.getVar('libdir', True)):
                os.symlink(src, dir)
}
