package com.hyzs.cidyth.common.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;

/**
 * 
 * @author derrick
 * 
 */
public class GenericBeanUtils {
	private static boolean ignoreNullValues = false;

	/**
	 * 将一个普通的JavaBean转换成Map,Map对象的key为JavaBean的属性名,value为属性值
	 * 
	 * @param bean
	 *            JavaBean对象实例
	 * @return 转换后的Map对象.
	 */
	public static final Map<String, Object> convertSimpleBean2Map(Object bean) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (bean != null) {
			return convertSimpleBean2Map(bean, ignoreNullValues, null, (String[]) null);
		}
		return result;
	}

	/**
	 * 将一个普通的JavaBean转换成Map,Map对象的key为Java Bean的属性名,value为属性值
	 * 
	 * @param bean
	 *            JavaBean对象实例
	 * @param ignoreNullValues
	 *            转换时，是否忽略JavaBean值为null的属性.
	 * @return 转换后的Map对象.
	 */
	public static final Map<String, Object> convertSimpleBean2Map(Object bean, boolean ignoreNullValues) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (bean != null) {
			return convertSimpleBean2Map(bean, ignoreNullValues, null, (String[]) null);
		}
		return result;
	}

	/**
	 * 将一个普通的JavaBean转换成Map,Map对象的key为Java Bean的属性名,value为属性值
	 * 
	 * @param bean
	 *            JavaBean对象实例
	 * @param ignoreNullValues
	 *            转换时，是否忽略JavaBean值为null的属性.
	 * @param ignoreProperties
	 *            转换时要忽略的属性名称组成的数组
	 * @return 转换后的Map对象.
	 */
	public static final Map<String, Object> convertSimpleBean2Map(Object bean, boolean ignoreNullValues,
			String... ignoreProperties) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (bean != null) {
			return convertSimpleBean2Map(bean, ignoreNullValues, null, ignoreProperties);
		}
		return result;
	}

	/**
	 * 将一个普通的JavaBean转换成Map,Map对象的key为Java Bean的属性名,value为属性值
	 * 
	 * @param bean
	 *            JavaBean对象实例
	 * @param ignoreNullValues
	 *            转换时，是否忽略JavaBean值为null的属性.
	 * @param propertyValueResolver
	 *            ConvertedBeanPropertyValueResolver类型的实例,
	 *            用于在转换时对JavaBean的每个属性值得自定义处理。
	 * @return 转换后的Map对象.
	 */
	public static final Map<String, Object> convertSimpleBean2Map(Object bean, boolean ignoreNullValues,
			ConvertedBeanPropertyValueResolver propertyValueResolver) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (bean != null) {
			return convertSimpleBean2Map(bean, ignoreNullValues, propertyValueResolver, (String[]) null);
		}
		return result;
	}

	/**
	 * 将一个普通的JavaBean转换成Map,Map对象的key为Java Bean的属性名,value为属性值
	 * 
	 * @param bean
	 *            JavaBean对象实例
	 * @param ignoreNullValues
	 *            转换时，是否忽略JavaBean值为null的属性.
	 * @param propertyValueResolver
	 *            ConvertedBeanPropertyValueResolver类型的实例,
	 *            用于在转换时对JavaBean的每个属性值得自定义处理。
	 * @param ignoreProperties
	 *            转换时要忽略的属性名称组成的数组
	 * @return 转换后的Map对象.
	 */
	public static final Map<String, Object> convertSimpleBean2Map(Object bean, boolean ignoreNullValues,
			ConvertedBeanPropertyValueResolver propertyValueResolver, String... ignoreProperties) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (bean != null) {
			PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(bean.getClass());
			List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
			boolean propertyValueResolverOn = propertyValueResolver == null ? false : true;
			for (PropertyDescriptor targetPd : pds) {
				Method readMethod = targetPd.getReadMethod();
				if (readMethod != null && !("getClass".equals(readMethod.getName()))
						&& (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
					try {
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(bean, new Object[] {});
						if (ignoreNullValues && value == null)
							continue;
						if (propertyValueResolverOn) {
							Object newValue = propertyValueResolver.resolvePropertyValue(targetPd.getName(), value);
							result.put(targetPd.getName(), newValue == null ? value : newValue);
						} else {
							result.put(targetPd.getName(), value);
						}
					} catch (Throwable ex) {
						throw new FatalBeanException("Could not fill property '" + targetPd.getName() + "' of bean["
								+ bean.getClass().getCanonicalName() + "] into HashMap", ex);
					}

				}
			}
		}
		return result;
	}

	/**
	 * JavaBean属性值拷贝替换,当源JavaBean的属性值与目标JavaBean的对应的属性值相同时,
	 * 将会用源JavaBean的属性值替换目标JavaBean的对应的属性值.
	 * 
	 * @param sourceBean
	 *            源JavaBean
	 * @param targetBean
	 *            目标JavaBean
	 */
	public static void copyAndReplace(Object sourceBean, Object targetBean) {
		copyAndReplace(sourceBean, targetBean, null);
	}

	/**
	 * JavaBean属性值拷贝替换,当源JavaBean的属性值与目标JavaBean的对应的属性值相同时,
	 * 将会用源JavaBean的属性值替换目标JavaBean的对应的属性值.
	 * 
	 * @param sourceBean
	 *            源JavaBean
	 * @param targetBean
	 *            目标JavaBean
	 * @param ignoreProperties
	 *            拷贝替换时要忽略的属性名称组成的数组
	 */
	public static void copyAndReplace(Object sourceBean, Object targetBean, List<String> ignoreProperties) {
		Class<?> actualEditable = targetBean.getClass();
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
		for (PropertyDescriptor targetPd : targetPds) {
			Method targetReadMethod = targetPd.getReadMethod();
			Method targetWriteMethod = targetPd.getWriteMethod();
			if (targetReadMethod != null && targetWriteMethod != null
					&& (ignoreProperties == null || (!ignoreProperties.contains(targetPd.getName())))) {
				PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(sourceBean.getClass(),
						targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null
							&& targetWriteMethod.getParameterTypes()[0].isAssignableFrom(readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object sourceValue = readMethod.invoke(sourceBean, new Object[] {});
							Object targetValue = targetReadMethod.invoke(targetBean, new Object[] {});
							if ((sourceValue == null && targetValue != null)
									|| (sourceValue != null && !sourceValue.equals(targetValue)
											|| !(sourceValue == targetValue))) {
								if (!Modifier.isPublic(targetWriteMethod.getDeclaringClass().getModifiers())) {
									targetWriteMethod.setAccessible(true);
								}
								targetWriteMethod.invoke(targetBean, new Object[] { sourceValue });
							}
						} catch (Throwable ex) {
							throw new FatalBeanException(
									"Could not copy value of '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}
	}

	public static interface ConvertedBeanPropertyValueResolver {
		public Object resolvePropertyValue(String propertyName, Object propertyValue);
	}

	/**
	 * 判断JavaBean的属性是否都为空值
	 * 
	 * @param bean
	 * @return true或者false.true表示参数bean的所有属性的值都为空,否则返回false.
	 */
	public static boolean isEmptyBean(Object bean) {
		return isEmptyBean(bean, null);
	}

	/**
	 * 判断JavaBean的指定属性是否都为空值
	 * 
	 * @param bean
	 * @param includeProperties
	 *            指定可为空的属性名组成的List
	 * @return true或者false.true表示参数bean的所有包含在includeProperties中的属性的值都为空,否则返回false.
	 */
	public static boolean isEmptyBean(Object bean, List<String> includeProperties) {
		if (bean == null) {
			throw new NullPointerException("parameter 'bean' is null.");
		}
		Class<?> actualEditable = bean.getClass();
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
		List<Boolean> results = null;
		if (includeProperties != null && includeProperties.size() > 0) {
			results = new ArrayList(includeProperties.size());
			int i = 0;
			for (PropertyDescriptor targetPd : targetPds) {
				Method targetReadMethod = targetPd.getReadMethod();
				if (targetReadMethod != null && !"getClass".equals(targetReadMethod.getName())) {
					if (includeProperties.contains(targetPd.getName())) {
						try {
							if (!Modifier.isPublic(targetReadMethod.getDeclaringClass().getModifiers())) {
								targetReadMethod.setAccessible(true);
							}
							Object targetValue = targetReadMethod.invoke(bean, new Object[] {});
							if (targetValue == null) {
								results.add(true);
							}
							i += 1;
						} catch (Throwable ex) {
							throw new RuntimeException(ex);
						}
					}
				}
			}
			return results.size() == includeProperties.size() ? true : false;
		} else {
			results = new ArrayList(targetPds.length);
			for (int i = 0; i < targetPds.length; i++) {
				PropertyDescriptor targetPd = targetPds[i];
				Method targetReadMethod = targetPd.getReadMethod();
				if (targetReadMethod != null && !"getClass".equals(targetReadMethod.getName())) {
					try {
						if (!Modifier.isPublic(targetReadMethod.getDeclaringClass().getModifiers())) {
							targetReadMethod.setAccessible(true);
						}
						Object targetValue = targetReadMethod.invoke(bean, new Object[] {});
						if (targetValue == null) {
							results.add(true);
						}
					} catch (Throwable ex) {
						throw new RuntimeException(ex);
					}
				}
			}
			return results.size() == targetPds.length ? true : false;
		}

	}

	/**
	 * 判断JavaBean的属性是否都不为空值
	 * 
	 * @param bean
	 * @return true或者false.true表示参数bean的所有属性的值都不为空,否则返回false.
	 */
	public static boolean isNotEmptyBean(Object bean) {
		return isNotEmptyBean(bean, null);
	}

	/**
	 * 判断JavaBean的指定属性是否都不为空值
	 * 
	 * @param bean
	 * @param includeProperties
	 *            指定不能为空的属性名组成的List
	 * @return true或者false.true表示参数bean的所有包含在includeProperties中的属性的值都不为空,否则返回false.
	 */
	public static boolean isNotEmptyBean(Object bean, List<String> includeProperties) {
		if (bean == null) {
			throw new NullPointerException("parameter 'bean' is null.");
		}
		Class<?> actualEditable = bean.getClass();
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
		List<Boolean> results = null;
		if (includeProperties != null && includeProperties.size() > 0) {
			results = new ArrayList(includeProperties.size());
			int i = 0;
			for (PropertyDescriptor targetPd : targetPds) {
				Method targetReadMethod = targetPd.getReadMethod();
				if (targetReadMethod != null && !"getClass".equals(targetReadMethod.getName())) {
					if (includeProperties.contains(targetPd.getName())) {
						try {
							if (!Modifier.isPublic(targetReadMethod.getDeclaringClass().getModifiers())) {
								targetReadMethod.setAccessible(true);
							}
							Object targetValue = targetReadMethod.invoke(bean, new Object[] {});
							if (targetValue != null) {
								results.add(true);
							}
							i += 1;
						} catch (Throwable ex) {
							throw new RuntimeException(ex);
						}
					}
				}
			}
			return results.size() == includeProperties.size() ? true : false;
		} else {
			results = new ArrayList(targetPds.length);
			for (int i = 0; i < targetPds.length; i++) {
				PropertyDescriptor targetPd = targetPds[i];
				Method targetReadMethod = targetPd.getReadMethod();
				if (targetReadMethod != null && !"getClass".equals(targetReadMethod.getName())) {
					try {
						if (!Modifier.isPublic(targetReadMethod.getDeclaringClass().getModifiers())) {
							targetReadMethod.setAccessible(true);
						}
						Object targetValue = targetReadMethod.invoke(bean, new Object[] {});
						if (targetValue != null) {
							results.add(true);
						}
					} catch (Throwable ex) {
						throw new RuntimeException(ex);
					}
				}
			}
			return results.size() == targetPds.length ? true : false;
		}

	}


}
