package project.ignythe.shopservice.api

import org.springframework.context.annotation.Import
import project.ignythe.shopservice.api.basket.BasketControllerModule
import project.ignythe.shopservice.api.item.ItemControllerModule

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import([BasketControllerModule, ItemControllerModule])
@interface IncludeApiConfiguration {

}
