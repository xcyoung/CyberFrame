package xcyoung.com.sample

/**
 * @author ChorYeung
 * @since 2018/10/29
 */
data class BeanList(val bean:Bean){
    data class Bean(val id:String = "0",val page:String = "0",val count:String = "0")
}