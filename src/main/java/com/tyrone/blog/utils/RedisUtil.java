package com.tyrone.blog.utils;

import com.tyrone.blog.annotation.SysLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    // ============================ common =============================

    /**
     * 设置缓存过期时间
     */
    public boolean setExpireTime(String key, long seconds) {
        try {
            if (seconds > 0) {
                redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error("Failed to set expire time for key: {}", key, e);
            return false;
        }
    }

    /**
     * 获取缓存过期时间
     */
    public long getExpireTime(String key) {
        return Optional.ofNullable(redisTemplate.getExpire(key, TimeUnit.SECONDS))
                .orElseThrow(() -> new RuntimeException("Failed to get expire time for key: " + key));
    }

    /**
     * 检查缓存中是否存在指定的键
     */
    @SysLog("用户注册")
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存中的键
     */
    public void deleteKey(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(Arrays.asList(keys)); // 使用 Arrays.asList() 方法
            }
        }
    }

    // ============================ String =============================

    /**
     * 获取缓存中的值
     */
    public Object getValue(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置缓存值
     */
    public boolean setValue(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置缓存值并指定过期时间
     */
    public boolean setValueWithExpireTime(String key, Object value, long seconds) {
        try {
            if (seconds > 0) {
                redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
            } else {
                setValue(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增缓存中的值
     */
    public long incrementValue(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("增量必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减缓存中的值
     */
    public long decrementValue(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减量必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    // ============================ Hash ================================

    /**
     * 获取Hash中的值
     */
    public Object getHashValue(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取整个Hash的所有键值对
     */
    public Map<Object, Object> getHashEntries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 设置Hash的多个键值对
     */
    public boolean setHashEntries(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置Hash的多个键值对并设置过期时间
     */
    public boolean setHashEntriesWithExpireTime(String key, Map<String, Object> map, long seconds) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (seconds > 0) {
                setExpireTime(key, seconds);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置Hash中的单个键值对
     */
    public boolean setHashValue(String key, String hashKey, Object value) {
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除Hash中的值
     */
    public void deleteHashValue(String key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * 判断Hash中是否存在某个键
     */
    public boolean hasHashKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * Hash值递增
     */
    public double incrementHashValue(String key, String hashKey, double delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    /**
     * Hash值递减
     */
    public double decrementHashValue(String key, String hashKey, double delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    // ============================ Set ================================

    /**
     * 获取Set中的所有值
     */
    public Set<Object> getSetMembers(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 向Set中添加值
     */
    public long addSetMembers(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 从Set中移除值
     */
    public long removeSetMembers(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ============================ List ================================

    /**
     * 获取List中的元素
     */
    public List<Object> getListRange(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 向List中添加元素
     */
    public boolean addListElement(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向List中添加元素并设置过期时间
     */
    public boolean addListElementWithExpireTime(String key, Object value, long seconds) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (seconds > 0) {
                setExpireTime(key, seconds);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引设置List中的值
     */
    public boolean updateListElementByIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从List中移除元素
     */
    public long removeListElements(String key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
