import request from '@/utils/request';
// list
export const jingsaixinxiList = (params) => {
    return request({
        url: '/jingsaixinxi/page',
        method: 'post',
        data: params,
    })
};
export const jingsaixinxiDetail = (id) => {
    return request({
        url: '/jingsaixinxi/'+id,
        method: 'get',
    })
};
// add
export const jingsaixinxiSave = (params) => {
    return request({
        url: '/jingsaixinxi',
        method: 'post',
        data: params,
    })
};
// updt
export const jingsaixinxiEdit = (params) => {
    return request({
        url: '/jingsaixinxi',
        method: 'put',
        data: params,
    })
};
// delete
export const jingsaixinxiDelete = (id) => {
    return request({
        url: '/jingsaixinxi/'+id,
        method: 'delete',
    })
};
// pldel
export const jingsaixinxiDeleteList = (jingsaixinxis) => {
    return request({
        url: '/jingsaixinxi/deleteList',
        data:{list:jingsaixinxis},
        method: 'post',
    })
};
// all
export const jingsaixinxiAllList = () => {
    return request({
        url: '/jingsaixinxi',
        method: 'get',
    })
};

export const jingsaixinxi_tj_cansairenshu = () => {return request({url: '/jingsaixinxi/jingsaixinxi_tj_cansairenshu',method: 'get',})};