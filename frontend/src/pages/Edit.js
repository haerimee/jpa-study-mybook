import { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import ReviewEditor from "../components/ReviewEditor";
import {call} from "../service/ApiService";

const Edit = () => {

    const [originData, setOriginData] = useState();
    const navigate = useNavigate();
    const { id } = useParams();

   // const bookList = useContext(DiaryStateContext);

    useEffect(() => {
        if (id) {
            call(`/api/review/${id}`,"GET", {})
                .then((res) => {
                   setOriginData(res)
                });
        }else{
            alert("없는 기록입니다.");
            navigate('/', { replace: true });
        }
    }, [id]);

    return (
        <div>
            {originData && <ReviewEditor isEdit={true} originData={originData} />}
        </div>
    );
};

export default Edit;