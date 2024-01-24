output "vnet_id" {
    value = azurerm_virtual_network.vnet1.id
}

output "private_subnet_association_id" {
    value = azurerm_subnet_network_security_group_association.private_subnet_association.id
}

output "public_subnet_association_id" {
    value = azurerm_subnet_network_security_group_association.public_sunbnet_association.id
}

output "private_subnet_name" {
    value = azurerm_subnet.private-subnet.name
}

output "public_subnet_name" {
    value = azurerm_subnet.public-subnet.name
}