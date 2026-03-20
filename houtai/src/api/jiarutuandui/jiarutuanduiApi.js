import request from '@/utils/request';
// list
export const jiarutuanduiList = (params) => {
    return request({
        url: '/jiarutuandui/page',
        method: 'post',
        data: params,
    })
};
export const jiarutuanduiDetail = (id) => {
    return request({
        url: '/jiarutuandui/'+id,
        method: 'get',
    })
};
// add
export const jiarutuanduiSave = (params) => {
    return request({
        url: '/jiarutuandui',
        method: 'post',
        data: params,
    })
};
// updt
export const jiarutuanduiEdit = (params) => {
    return request({
        url: '/jiarutuandui',
        method: 'put',
        data: params,
    })
};
// delete
export const jiarutuanduiDelete = (id) => {
    return request({
        url: '/jiarutuandui/'+id,
        method: 'delete',
    })
};
// pldel
export const jiarutuanduiDeleteList = (jiarutuanduis) => {
    return request({
        url: '/jiarutuandui/deleteList',
        data:{list:jiarutuanduis},
        method: 'post',
    })
};
// all
export const jiarutuanduiAllList = () => {
    return request({
        url: '/jiarutuandui',
        method: 'get',
    })
};

