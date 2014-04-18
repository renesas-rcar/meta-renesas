python __anonymous () {
    srcuri = d.getVar('SRC_URI', True)
    pv = d.getVar('PV', True)
    newsrcuri=""
    for uri in srcuri.split():
        if "releases.qt-project.org" not in uri:
            newsrcuri = newsrcuri + uri + " "
        else:
            newsrcuri = newsrcuri + "http://download.qt-project.org/archive/qt/4.8/" + pv + "/qt-everywhere-opensource-src-" + pv + ".tar.gz "
    d.setVar('SRC_URI', newsrcuri)
}