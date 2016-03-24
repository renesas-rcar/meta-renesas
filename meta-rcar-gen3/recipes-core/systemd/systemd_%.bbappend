inherit groupadd_useradd_prepend

EXTRA_OECONF += " \
    --disable-timesyncd \
"
