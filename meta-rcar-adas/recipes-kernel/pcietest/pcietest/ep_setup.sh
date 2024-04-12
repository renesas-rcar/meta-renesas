#!/bin/sh
# SPDX-License-Identifier: GPL-2.0
echo "Endpoint setup"

mount -t configfs none /sys/kernel/config
sleep 1
cd /sys/kernel/config/pci_ep
mkdir functions/pci_epf_test/func1
sleep 1
cd /sys/kernel/config/pci_ep
echo 0x1912 > functions/pci_epf_test/func1/vendorid
echo 0x0030 > functions/pci_epf_test/func1/deviceid
echo 32 > functions/pci_epf_test/func1/msi_interrupts
sleep 1
cd /sys/kernel/config/pci_ep
ln -s functions/pci_epf_test/func1/ controllers/e65d0000.pcie-ep/
sleep 1
cd /sys/kernel/config/pci_ep
echo 1 > controllers/e65d0000.pcie-ep/start
echo "completed."
