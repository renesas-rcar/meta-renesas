# This bbclass removes the lib32 packages when populate_sdk for core-image-*,
# To create SDK for 32bit libraries, target image must be lib32-core-image-*
LIB32_IMG_PKG_BLACKLIST = "weston-init kernel-devsrc"

python __anonymous () {
    pn = d.getVar('PN', True)
    if pn.startswith('lib32-'):
        pkgs_bl = d.getVar('LIB32_IMG_PKG_BLACKLIST', True)
        img_install = d.getVar('IMAGE_INSTALL', True)
        lib32_sdk_pkgs_install = []
        for p in img_install.split():
            if p not in pkgs_bl.split():
                lib32_sdk_pkgs_install.append(p)
        d.setVar('IMAGE_INSTALL', ' '.join(lib32_sdk_pkgs_install))
        img_install = d.getVar('IMAGE_INSTALL', True)

    target_arch = d.getVar('TARGET_ARCH', True)
    tc_target_task = d.getVar('TOOLCHAIN_TARGET_TASK', True)
    revise_tc_target_task = ''
    if target_arch == "aarch64":
        list = []
        for pkgs in tc_target_task.split():
            if not pkgs.startswith('lib32-'):
                list.append(pkgs)
    d.setVar('TOOLCHAIN_TARGET_TASK', ' '.join(list))
}

MULTILIBRE_ALLOW_REP =. "${includedir}|"
