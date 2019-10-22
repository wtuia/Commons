/**
 * 复合转发 <br/>
 * 如果子类和超类处在不同的包中，并且超类并不是为了继承而设计的，那么继承将会导致
 * 脆弱性( fragility ） 。 为了避免这种脆弱性，可以用复合和转发机制来代替继承，
 * 尤其是当存在适当的接口可以实现包装类的时候 。 包装类不仅比子类更加健壮，而且
 * 功能也更加强大
 * <br/>
 * <br/>
 * 修饰者模式
 * InstrumentSet 对一个集合进行了修饰，并提供了新特性
 * <br/>
 * <br/>
 * 如果无法确定两个对象之间一定是is-a的关系，就可以使用复合转发替代继承。
 * 一个由继承产生的问题：
 * @see java_util.properties.PropertiesDemo#getProperty()
 */
package since.composition_forward;